package com.xbrain.vendasapi.services;

import com.xbrain.vendasapi.controllers.dto.VendaRequest;
import com.xbrain.vendasapi.domain.Venda;

public interface VendaService {

    Venda gerarVenda(VendaRequest vendaRequest);


}
