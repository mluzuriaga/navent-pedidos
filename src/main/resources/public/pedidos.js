$(document).ready(() => {

    new Pedidos();

});

class Pedidos {

    constructor() {

        this.setSubmitEvent();

    }

    /**
     * Evento de submit del formulario
     */
    setSubmitEvent() {

        $("#pedidos-form").submit((event) => {

            event.preventDefault();

            let data = this.getFormData();
            this.sendData(data);

        });

    }

    /**
     * Obtengo la informacion del formulario
     */
    getFormData() {

        let data = {};

        data.nombre = $('#nombre').val();
        data.monto = $('#monto').val();
        data.descuento = $('#descuento').val();

        return data;

    }

    /**
     * Envia los datos al servidor
     *
     * @param data
     */
    sendData(data) {

        $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/pedidos',
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(data),
                success: () => {
                    alert("Pedido dado de alta correctamente.")
                    this.clearData();
                },
                error: (request, status, error) => {
                    alert(request.responseText);
                }
            }
        )

    }

    /**
     * Limpia los campos del formulario
     */
    clearData() {

        $('#nombre').val('');
        $('#monto').val('');
        $('#descuento').val('');

    }

}
