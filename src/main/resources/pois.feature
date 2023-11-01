Feature: Devolver el punto de interés más cercano.

Scenario: La aplicación debe retornar el punto de interés más cercano
Given: la aplicación esta levantada.
When: El usuario solicita el punto de interés más cercano.
Then: El usuario recibe la respuesta del sistema con status 200 y el body
"""
{
"Punto más cercano": Tostado Café 200m
}
"""