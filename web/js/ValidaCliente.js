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

    if (nome.length < 5)
    {
        document.AcaoCliente.nome.style.backgroundColor = "#94949e";
        document.AcaoCliente.nome.focus();
        erro++;
    } else {
        document.AcaoUsuario.Nome.style.backgroundColor = "white";
    }
    if (!TestaCPF(cpf))
    {
        document.AcaoCliente.cpf.style.backgroundColor = "#94949e";
        erro++;
    } else {
        document.AcaoCliente.cpf.style.backgroundColor = "white";
    }

    if (data_nsci.length < 10)
    {
        document.AcaoCliente.data_nsci.style.backgroundColor = "#94949e";
        erro++;
    } else {
        document.AcaoCliente.data_nsci.style.backgroundColor = "white";
    }

    if (email.length < 9)
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

function TestaCPF(strCPF) {
    var Soma;
    var Resto;
    Soma = 0;
    CPF = strCPF.replaceAll(".", "").replaceAll("-", "");
    console.log(CPF);

    if (CPF == "00000000000")
        return false;

    for (i = 1; i <= 9; i++)
        Soma = Soma + parseInt(CPF.substring(i - 1, i)) * (11 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10)(Resto == 11))
        Resto = 0;
    if (Resto != parseInt(CPF.substring(9, 10)))
        return false;

    Soma = 0;
    for (i = 1; i <= 10; i++)
        Soma = Soma + parseInt(CPF.substring(i - 1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10)(Resto == 11))
        Resto = 0;
    if (Resto != parseInt(CPF.substring(10, 11)))
        return false;
    return true;
}

function validateDate(id) {
    var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])      [\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;

    if (!((id.value.match(RegExPattern)) && (id.value != ''))) {
        return false;
    } else
        return true;
}

