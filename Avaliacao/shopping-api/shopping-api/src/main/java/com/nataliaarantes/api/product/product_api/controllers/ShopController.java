  package com.nataliaarantes.api.product.product_api.controllers;

  import com.nataliaarantes.api.product.product_api.models.dto.ShopDTO;
  import com.nataliaarantes.api.product.product_api.services.ShopService;
  import lombok.RequiredArgsConstructor;
  import org.springframework.http.HttpStatus;
  import org.springframework.web.bind.annotation.*;

  import java.io.IOException;
  import java.util.List;

  @RestController
  @RequestMapping("/shopping")
  @RequiredArgsConstructor
  public class ShopController {
    private final ShopService shopService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ShopDTO save(@RequestBody ShopDTO shopDTO) throws IOException, InterruptedException {
      return shopService.save(shopDTO);
    }

    @GetMapping
    public List<ShopDTO> getAll() {
      return shopService.getAll();
    }

    @GetMapping("/{id}")
    public ShopDTO getById(@PathVariable String id) {
      return shopService.findById(id);
    }

    @GetMapping("/shopByUser/{cpf}")
    public List<ShopDTO> getByUser(@PathVariable String cpf) throws IOException, InterruptedException {
      return shopService.findByUser(cpf);
    }

    @GetMapping("/shopByProduct/{identifier}")
    public List<ShopDTO> findByProductIdentifier(@PathVariable String identifier) throws IOException, InterruptedException {
      return shopService.findByProductIdentifier(identifier);
    }

    @GetMapping("/shopByDate/{date}")
    public List<ShopDTO> getByDate(@PathVariable String date) throws IOException, InterruptedException {
      return shopService.findByDate(date);
    }

    @GetMapping("/search")
    public List<ShopDTO> getShopsByFilter(
        @RequestParam(required = false) String dataInicio,
        @RequestParam(required = false) String dataFim,
        @RequestParam(required = false) Double valorMinimo
    ) {
      return shopService.getShopsByFilter(dataInicio, dataFim, valorMinimo);
    }

    @GetMapping("/report")
    public Object getReportByDate(
        @RequestParam String dataInicio,
        @RequestParam String dataFim
    ) {
      return shopService.getReportByDate(dataInicio, dataFim);
    }

  }
