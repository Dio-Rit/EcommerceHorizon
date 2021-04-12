/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validardadosProduto() {
    var erro = 0;

    nome = document.AcaoProduto.nome.value;
    quantidade = document.AcaoProduto.quantidade.value;
    preco = document.AcaoProduto.preco.value;
    descricao = document.AcaoProduto.descricao.value;


    if (nome.length < 3)
    {
        document.AcaoProduto.nome.style.backgroundColor = "#94949e";
        document.AcaoProduto.nome.focus();
        erro++;
    } else {
        document.AcaoProduto.nome.style.backgroundColor = "white";
    }

    if (quantidade.length < 1)
    {
        document.AcaoProduto.quantidade.style.backgroundColor = "#94949e";
        erro++;
    } else {
        document.AcaoProduto.quantidade.style.backgroundColor = "white";
    }

    if (preco.length < 1)
    {
        document.AcaoProduto.preco.style.backgroundColor = "#94949e";
        erro++;
    } else {
        document.AcaoProduto.preco.style.backgroundColor = "white";
    }

    if (erro > 0) {
        window.alert("Preencha os campos corretamente!");
        return false;
    } else {
        return true;
    }
}
