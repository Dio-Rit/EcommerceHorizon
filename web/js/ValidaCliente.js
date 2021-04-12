/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validardadosCliente() {
    var erro = 0;

    nome = document.AcaoCliente.nome.value;
    cpf = document.AcaoCliente.cpf.value;
    data_nsci = document.AcaoCliente.data_nsci.value;
    email = document.AcaoCliente.email.value;

    if (nome.length < 5 && nome.length > 100)
    {
        document.AcaoCliente.nome.style.backgroundColor = "#94949e";
        document.AcaoCliente.nome.focus();
        erro++;
    } else {
        document.AcaoUsuario.Nome.style.backgroundColor = "white";
    }
    if (cpf.length <14) 
    {
        document.AcaoCliente.cpf.style.backgroundColor = "#94949e";
        erro++;
    } else {
        document.AcaoCliente.cpf.style.backgroundColor = "white";
    }

    if (data_nsci.length <10)
    {
        document.AcaoCliente.data_nsci.style.backgroundColor = "#94949e";
        erro++;
    } else {
        document.AcaoCliente.data_nsci.style.backgroundColor = "white";
    }

    if (email.length < 9 && email.length > 50)
    {
        document.AcaoCliente.email.style.backgroundColor = "#94949e";
        erro++;
    } else {
        document.AcaoCliente.email.style.backgroundColor = "white";
    }

    if (erro > 0) {
        window.alert("Preencha os campos corretamente!");
        return false;
    } else {
        return true;
    }
}

