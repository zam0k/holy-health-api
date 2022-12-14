package io.github.zam0k.HolyHealth.rest.controller;

import io.github.zam0k.HolyHealth.domain.entities.Client;
import io.github.zam0k.HolyHealth.rest.dto.ClientDTO;
import io.github.zam0k.HolyHealth.rest.dto.RiskierClientDTO;
import io.github.zam0k.HolyHealth.rest.dto.UpdateClientDTO;
import io.github.zam0k.HolyHealth.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @GetMapping
    public List<ClientDTO> showAll() {
        List<ClientDTO> clients = service.listAll();
        return clients;
    }

    @GetMapping("/{id}")
    public ClientDTO getClientById(@PathVariable UUID id) {
        ClientDTO client = service.getById(id);
        return client;
    }

    @GetMapping("/riskier")
    public Set<RiskierClientDTO> getTenRiskierClients() {
        return service.listRiskier();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Client save(@Valid @RequestBody ClientDTO dto) {
        Client client = service.save(dto);
        return client;
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable UUID id,
                       @Valid
                       @RequestBody UpdateClientDTO client) {
        service.update(client, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.remove(id);
    }

}
