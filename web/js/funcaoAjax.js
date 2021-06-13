function carregaPaginaProdutos(pagina) {

    //  document.getElementById('insere_aqui').style.display = "inline"; // mostra div    

    var requisicaoAjax = iniciaAjax();

    if (requisicaoAjax) {
        requisicaoAjax.onreadystatechange = function () {

            if (requisicaoAjax.readyState === 4) {
                if (requisicaoAjax.status === 200 || requisicaoAjax.status === 304) {
                    document.getElementById("retornoAjax").innerHTML = requisicaoAjax.responseText;
                }
            }
        };

        requisicaoAjax.open("POST", pagina, true);
        requisicaoAjax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')

        var form = document.forms['formulario'];
        var nome = form.nome.value;
        var precoInicial = form.precoInicial.value;
        var precoFinal = form.precoFinal.value;
        var quantidade = form.quantidade.value;

        qstr = 'nome=' + escape(nome);
        qstr += "&precoInicial=" + escape(precoInicial);
        qstr += "&precoFinal=" + escape(precoFinal);
        qstr += "&quantidade=" + escape(quantidade);
        console.log(qstr);
        requisicaoAjax.send(qstr);
        return true;
    } else {
        return false;
    }
}

function carregaPaginaCliente(pagina) {

    //  document.getElementById('insere_aqui').style.display = "inline"; // mostra div    

    var requisicaoAjax = iniciaAjax();

    if (requisicaoAjax) {
        requisicaoAjax.onreadystatechange = function () {

            if (requisicaoAjax.readyState === 4) {
                if (requisicaoAjax.status === 200 || requisicaoAjax.status === 304) {
                    document.getElementById("retornoAjax").innerHTML = requisicaoAjax.responseText;
                }
            }
        };

        requisicaoAjax.open("POST", pagina, true);
        requisicaoAjax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded')

        var form = document.forms['formulario'];
        var nome = form.nome.value;
        var cpf = form.cpf.value;
        var dataInicial = form.dataInicial.value;
        var dataFinal = form.dataFinal.value;

        qstr = 'nome=' + escape(nome);
        qstr += "&cpf=" + escape(cpf);
        qstr += "&dataInicial=" + escape(dataInicial);
        qstr += "&dataFinal=" + escape(dataFinal);

        console.log(qstr);
        requisicaoAjax.send(qstr);
        return true;
    } else {
        return false;
    }
}

// Funcao que cria objeto Ajax XMLHttpRequest
function iniciaAjax() {
    var objetoAjax = false;

    if (window.XMLHttpRequest) { // tenta carregar componente Mozilla/safari
        objetoAjax = new XMLHttpRequest();
    } else if (window.ActiveXObject) { // se falhar, tenta carregar o ActiveX do IE :-(
        try {
            objetoAjax = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                objetoAjax = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (ex) {
                objetoAjax = false;
            }
        }
    }
    return objetoAjax;
}
