/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function validardadosFornecedor() {
    var erro = 0;

    nome = document.AcaoFornecedor.nome.value;
    cnpj = document.AcaoFornecedor.cnpj.value;
    telefone = document.AcaoFornecedor.telefone.value;
    email = document.AcaoFornecedor.email.value;

    if (nome.length < 5)
    {
        document.AcaoFornecedor.nome.style.backgroundColor = "#94949e";
        erro++;
    } else {
        document.AcaoFornecedor.nome.style.backgroundColor = "white";
    }

    if (cnpj.length < 18)
    {
        document.AcaoFornecedor.cnpj.style.backgroundColor = "#94949e";
        erro++;
    } else {
        document.AcaoFornecedor.cnpj.style.backgroundColor = "white";
    }

    if (telefone.length < 14)
    {
        document.AcaoFornecedor.telefone.style.backgroundColor = "#94949e";
        erro++;
    } else {
        document.AcaoFornecedor.telefone.style.backgroundColor = "white";
    }
    
    if (email.length < 9)
    {
        document.AcaoFornecedor.email.style.backgroundColor = "#94949e";
        erro++;
    } else {
        document.AcaoFornecedor.email.style.backgroundColor = "white";
    }

    if (erro > 0) {
        window.alert("Preencha os campos corretamente!");
        return false;
    } else {
        return true;
    }
}