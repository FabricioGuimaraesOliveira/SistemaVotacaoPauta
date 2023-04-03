package com.sistema.votacao.infrastructure.adapters.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CallValidateCpfService {
    @Value("${endpoint.valida.cpf.host}")
    private String url;
    private RestTemplate restTemplate;

    public Boolean isValidaCpf(String cpf) {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("cpf", cpf);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        var urlConsult = builder.buildAndExpand(urlParams).toString();

        try {
            var respone = restTemplate.exchange(urlConsult, HttpMethod.GET, getEntity(), String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
    }

    private HttpEntity<Object> getEntity() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(httpHeaders);
    }
}
