package com.sousa.meal_planner.models.dto.chatGPT;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRequest {

    private String model;
    private List<Message> messages;
    private int n = 1;
    private double temperature;

    public ChatRequest(String model, String prompt, String imageBase64) {
        this.model = model;

        this.messages = new ArrayList<>();
        this.messages.add(createBaseMessage());
        this.messages.add(createBaseImageMessage());
        this.messages.add(Message.builder().role("user").content(prompt).build());
        this.messages.add(Message.builder().role("user").content(imageBase64).build());
    }

    public ChatRequest(String model, String prompt) {
        this.model = model;

        this.messages = new ArrayList<>();
        this.messages.add(createBaseMessage());
        this.messages.add(Message.builder().role("user").content(prompt).build());
    }

    private Message createBaseImageMessage() {
        return new Message("developer", "\"Context:\\nTenho um talão de supermercado com vários artigos, incluindo itens que não são relevantes para a despensa de carnes, peixes ou refeições prontas (como azeites, especiarias, cápsulas de café, guardanapos etc.). Quero que analises a imagem do talão e extraias somente os produtos relevantes para o planeamento de refeições (por exemplo, carnes, peixes, pizzas, sobremesas como carrot cake). Se não tiveres certeza se o item é relevante, inclui-o mesmo assim, pois depois posso filtrar manualmente.\\n\\nObjetivo:\\nGerar uma resposta em JSON que liste todos os produtos relevantes encontrados no talão, com o nome do produto, o tipo do item e o seu preço, além de um campo fixo \\\"portions\\\" sempre igual a 0. A resposta deve ter o seguinte formato:\\n\\n```json\\n{\\n  \\\"answer\\\": \\\"Frase livre descrevendo a análise, por exemplo: estes são os itens relevantes do talão\\\",\\n  \\\"Items\\\": [\\n    {\\n      \\\"itemName\\\": \\\"NOME_DO_PRODUTO\\\",\\n      \\\"itemType\\\": \\\"MEAT | FISH | DAIRY | VEGETABLE | FRUIT | GRAIN | OTHER\\\",\\n      \\\"price\\\": VALOR_PRECO,\\n      \\\"portions\\\": 0\\n    }\\n  ]\\n}\\n```\\n\\nRegras e Detalhes Importantes:\\n1. Ignorar produtos que claramente não sejam carnes, peixes, pizzas, sobremesas ou similares (ex.: cápsulas de café, especiarias, azeites, guardanapos).\\n2. Incluir carnes, peixes, pizzas, sobremesas (ex.: carrot cake). Se não tiver certeza se o item se encaixa, inclui-o na lista.\\n3. Para cada item incluído, adicionar `portions: 0`.\\n4. Usar o campo `\\\"price\\\"` como um valor numérico (por exemplo, 1.95 ou 2.60).\\n5. O campo `\\\"answer\\\"` pode conter um texto livre, como: \\\"Estes são os produtos relevantes que encontrei.\\\".\\n6. Para o campo `\\\"itemType\\\"`, usar uma das seguintes opções: `\\\"MEAT\\\"`, `\\\"FISH\\\"`, `\\\"DAIRY\\\"`, `\\\"VEGETABLE\\\"`, `\\\"FRUIT\\\"`, `\\\"GRAIN\\\"`, ou `\\\"OTHER\\\"` (caso não tenhas certeza).\\n\\nExemplo Simplificado de Saída Esperada:\\n\\n```json\\n{\\n  \\\"answer\\\": \\\"Esses são os itens relevantes encontrados no talão.\\\",\\n  \\\"Items\\\": [\\n    {\\n      \\\"itemName\\\": \\\"PIZZA BBQ M.FINA\\\",\\n      \\\"itemType\\\": \\\"OTHER\\\",\\n      \\\"price\\\": 2.60,\\n      \\\"portions\\\": 0\\n    },\\n    {\\n      \\\"itemName\\\": \\\"CARROT CAKE\\\",\\n      \\\"itemType\\\": \\\"OTHER\\\",\\n      \\\"price\\\": 1.90,\\n      \\\"portions\\\": 0\\n    }\\n  ]\\n}\\n```\\n\\nTarefa:\\n- Ler a imagem do talão.\\n- Identificar cada produto que seja de carne (MEAT), peixe (FISH), laticínios (DAIRY), vegetais (VEGETABLE), frutas (FRUIT), grãos (GRAIN) ou outro (OTHER). Caso não tenhas certeza, usa `\\\"OTHER\\\"`.\\n- Excluir itens obviamente não relevantes (ex.: \\\"capsulas cafe\\\", \\\"especiarias\\\", \\\"guardanapos\\\", etc.).\\n- Gerar o JSON final no formato indicado acima, listando item a item com `\\\"itemName\\\"`, `\\\"itemType\\\"`, `\\\"price\\\"` e `\\\"portions\\\": 0`.\\n\"");
    }

    public Message createBaseMessage() {
        return new Message("developer", "You are the assistant of the user Nuno Sousa. Your name will be Atlas. You should always answer in a cordial manner with a slight bit of humor. A polite and educated but nice humor.");
    }

}

