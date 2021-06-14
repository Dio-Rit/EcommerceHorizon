/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validardados() {
    var erro = 0;

    nome = document.AcaoUsuario.Nome.value;
    login = document.AcaoUsuario.Login.value;
    if (document.AcaoUsuario.Senha.value != null) {
        senha = document.AcaoUsuario.Senha1.value;
    } else {
        senha = document.AcaoUsuario.Senha1.value;
    }


    if (nome.length < 5)
    {
        document.AcaoUsuario.Nome.style.backgroundColor = "#94949e";
        document.AcaoUsuario.Nome.focus();
        erro++;
    } else {
        document.AcaoUsuario.Nome.style.backgroundColor = "white";
    }

    if (login.length < 3)
    {
        document.AcaoUsuario.Login.style.backgroundColor = "#94949e";
        erro++;
    } else {
        document.AcaoUsuario.Login.style.backgroundColor = "white";
    }

    if (senha.length < 8)
    {
        document.AcaoUsuario.Senha.style.backgroundColor = "#94949e";
        erro++;
    } else {
        document.AcaoUsuario.Senha.style.backgroundColor = "white";
    }

    if (erro > 0) {
        window.alert("Preencha os campos corretamente!");
        return false;
    } else {
        return true;
    }
}

