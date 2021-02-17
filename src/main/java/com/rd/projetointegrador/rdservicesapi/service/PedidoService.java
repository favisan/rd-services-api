package com.rd.projetointegrador.rdservicesapi.service;

import com.rd.projetointegrador.rdservicesapi.dto.AgServico;
import com.rd.projetointegrador.rdservicesapi.dto.Cartao;
import com.rd.projetointegrador.rdservicesapi.dto.Servico;
import com.rd.projetointegrador.rdservicesapi.entity.*;
import com.rd.projetointegrador.rdservicesapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PedidoService {

    // List<AgServicoEntity> agendamentos
    //percorrer o array de ag e pegar o serviço e o valor que vai ser exibido na página de pgmto.
    //Calcular total.
    //Se for plano, se o plano cobre o serviço. Quais serviços o plano cobre.
    //Recolher dados do cartão.
    //Ordem de salvamento:
    // 1 - Salvar cartão;
    // 2 - Salvar pedido;
    // 3 - Salvar agendamento;
    // 4 - Salvar pagamento.
    // Ir para página de confirmação.
    // Mostrar lista de agendamento na página de confirmação.

    /*
    {
        "idUsuario": 1,
            "agendamentos": [ {"idServico", "idLoja", "idStatus", "dateDataHora"
    },
        { "idServico", "idLoja", "idStatus", "dateDataHora"
        }],
        "cartao": { "idUsuario", "nrCartao", "codSeguranca", "dataValidade", "dataEmissao"}
    }
    */

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private AgServicoRepository agServicoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired private TipoPagamentoRepository tpRepository;


    SimpleDateFormat SDF2 = new SimpleDateFormat("yyyy-MM-dd");

    @Transactional
    public String cadastrarAgendamento (List<AgServico> lista, BigInteger id, Cartao cartao) {
       List<AgServicoEntity> listaEntity = new ArrayList<>();
       List<Servico> servicoDto = new ArrayList<>();
       UsuarioEntity user = usuarioRepository.findById(id).get();

        double totalPedido = 0;

        for (AgServico ag : lista ){
            AgServicoEntity agServicoEntity = new AgServicoEntity();
            agServicoEntity.setIdServico(servicoRepository.findById(ag.getIdServico()).get());
            agServicoEntity.setIdLoja(lojaRepository.findById(ag.getIdLoja()).get());
            agServicoEntity.setIdStatus(statusRepository.findById(ag.getIdStatus()).get());
            agServicoEntity.setDtDataHora(ag.getDtDataHora());
            listaEntity.add(agServicoEntity);

            totalPedido += agServicoEntity.getIdServico().getPreco();
        }

        List<ContratoEntity> listContratoEntity = contratoRepository.findByUsuarioOrderByDtVigencia(user);
        ContratoEntity contratoEntity = listContratoEntity.get(0);

        List<ServicoPlanoEntity> listaServicos = contratoEntity.getPlanosEntity().getServicos();
        boolean cobreServico = false;

        for (ServicoPlanoEntity s : listaServicos) {
            if (s.getIdServicoPlano() == BigInteger.valueOf(1L)) {
                cobreServico = true;
            }
        }

        // 2 - Salvar pedido;
        // 3 - Salvar agendamento;
        // 4 - Salvar pagamento.

        try { PedidoEntity pedido  = new PedidoEntity();
            pedido.setIdPaciente(id);
            pedido.setVlTotal(totalPedido);

            PedidoEntity pedidoSalvo = pedidoRepository.save(pedido);

        for (AgServicoEntity ag : listaEntity) {
            ag.setPedido(pedidoSalvo);
            agServicoRepository.save(ag);
        }

        if (!cobreServico) {
            CartaoEntity cartaoEntity = new CartaoEntity();
            cartaoEntity.setNrCartao(cartao.getNrCartao());
            cartaoEntity.setCodSeguranca(cartao.getCodSeguranca());

            Date dataEmissao = SDF2.parse(cartao.getDtEmissao());
            cartaoEntity.setDtEmissao(dataEmissao);
            Date dataValidade = SDF2.parse(cartao.getDtValidade());
            cartaoEntity.setDtValidade(dataValidade);
            cartaoEntity.setUsuario(user);

            cartaoRepository.save(cartaoEntity);

            PagamentoEntity pagamento = new PagamentoEntity();
            pagamento.setIdCartao(cartao.getIdCartao());
            TipoPagamentoEntity tipoPagamentoEntity = tpRepository.findById(BigInteger.valueOf(1L)).get();
            pagamento.setTipoPagamentoEntity(tipoPagamentoEntity);
            pagamento.setIdPedido(pedidoSalvo.getIdPedido());
            pagamento.setVlPagamento(totalPedido);
            pagamento.setDtPagamento(new Date());

            pagamentoRepository.save(pagamento);
            return "Agendamento realizado com sucesso";
        }

        PagamentoEntity pagamento = new PagamentoEntity();
        TipoPagamentoEntity tipoPagamentoEntity = tpRepository.findById(BigInteger.valueOf(3L)).get();
        pagamento.setTipoPagamentoEntity(tipoPagamentoEntity);
        pagamento.setIdPedido(pedidoSalvo.getIdPedido());
        pagamento.setVlPagamento(totalPedido);
        pagamento.setDtPagamento(new Date());

        pagamentoRepository.save(pagamento);
        return "Agendamento realizado com sucesso";
        }

        catch (Exception e) {
            return "Erro ao criar agendamento.";
        }
    }
}
