Feature: Verificacion de aplicación levantada

    Scenario: La aplicacion esta levantada y respondiendo
        Given la aplicacion está levantada
        When el cliente hace un GET a "/ping"
        Then recibe la respuesta con codigo de estado 200 y contenido
        """
        {
            "mensaje": "pong"
        }
        """