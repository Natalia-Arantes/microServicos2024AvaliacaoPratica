package com.nataliaarantes.api.product.product_api.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nataliaarantes.api.product.product_api.models.Item;
import com.nataliaarantes.api.product.product_api.models.Shop;
import com.nataliaarantes.api.product.product_api.models.dto.ItemDTO;
import com.nataliaarantes.api.product.product_api.models.dto.ShopDTO;
import com.nataliaarantes.api.product.product_api.repositories.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

  private final ShopRepository shopRepository;
  private final HttpClient client = HttpClient.newHttpClient();
  private final ObjectMapper objectMapper = new ObjectMapper();


  private final String USER_API_URL = "http://localhost:8080";
  private final String PRODUCT_API_URL = "http://localhost:8081";


  private String findUserByCpf(String cpf) throws IOException, InterruptedException {
    HttpRequest userRequest = HttpRequest.newBuilder()
        .uri(URI.create(MessageFormat.format("{0}/user/{1}/cpf", USER_API_URL, cpf)))
        .GET()
        .build();

    HttpResponse<String> userResponse = client.send(userRequest, HttpResponse.BodyHandlers.ofString());
    if (userResponse.statusCode() != 200 || userResponse.body().isEmpty()) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "User not found");
    }

    return cpf;
  }

  private JsonNode findProductByIdentifier(String Identifier) throws IOException, InterruptedException {
    HttpRequest productRequest = HttpRequest.newBuilder()
        .uri(URI.create(MessageFormat.format("{0}/product/{1}/identifier", PRODUCT_API_URL, Identifier)))
        .GET()
        .build();

    HttpResponse<String> productResponse = client.send(productRequest, HttpResponse.BodyHandlers.ofString());
    if (productResponse.statusCode() != 200 || productResponse.body().isEmpty()) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Product " + Identifier + " not found");
    }

    JsonNode obj = objectMapper.readTree(productResponse.body());
    return obj;
  }

  public ShopDTO save(ShopDTO dto) throws IOException, InterruptedException {
    Shop shop = new Shop();
    shop.setDate(LocalDate.now());
    shop.setItens(new ArrayList<>());

    String userCpf = dto.getUserIdentifier();

    this.findUserByCpf(userCpf);
    shop.setUserId(userCpf);

    for (ItemDTO item : dto.getItens()) {
      JsonNode response = findProductByIdentifier(item.getProductIdentifier());
      shop.getItens().add(new Item(response.get("productIdentifier").asText(), response.get("preco").asDouble()));
    }

    Double total = shop.getItens()
        .stream()
        .mapToDouble(Item::getPrice)
        .sum();

    shop.setTotal(total);
    shop = shopRepository.save(shop);
    return ShopDTO.convert(shop);
  }

  public List<ShopDTO> getAll() {
    List<Shop> allProducts = shopRepository.findAll();

    return allProducts.stream()
        .map(ShopDTO::convert)
        .toList();
  }

  public ShopDTO findById(String id) {
    Shop shop = shopRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    return ShopDTO.convert(shop);
  }

  public List<ShopDTO> findByUser(String cpf) throws IOException, InterruptedException {
    findUserByCpf(cpf);
    List<Shop> shops = shopRepository.findByUserId(cpf);

    return shops.stream().map(ShopDTO::convert).toList();
  }

  public List<ShopDTO> findByProductIdentifier(String identifier) throws IOException, InterruptedException {
    findProductByIdentifier(identifier);
    List<Shop> shops = shopRepository.findByProductIdentifier(identifier);

    return shops.stream().map(ShopDTO::convert).toList();
  }

  public List<ShopDTO> findByDate(String date) {
    LocalDate convertedDate = LocalDate.parse(date);
    List<Shop> shops = shopRepository.findByDate(convertedDate);

    return shops.stream().map(ShopDTO::convert).toList();
  }

  public List<ShopDTO> getShopsByFilter(String dataInicio, String dataFim, Double valorMinimo) {
    LocalDate startDate = (dataInicio != null) ? LocalDate.parse(dataInicio) : null;
    LocalDate endDate = (dataFim != null) ? LocalDate.parse(dataFim) : null;

    List<Shop> filteredShops = shopRepository.findByFilters(startDate, endDate, valorMinimo);
    return filteredShops.stream().map(ShopDTO::convert).toList();
  }

  public Object getReportByDate(String dataInicio, String dataFim) {
    LocalDate startDate = LocalDate.parse(dataInicio);
    LocalDate endDate = LocalDate.parse(dataFim);

    List<Shop> shops = shopRepository.findByDateRange(startDate, endDate);

    return new Object() {
      public double totalValue = shops.stream().mapToDouble(Shop::getTotal).sum();;
      public long totalItems = shops.stream().flatMap(shop -> shop.getItens().stream()).count();;
      public final int totalShops = shops.size();
    };
  }





}
