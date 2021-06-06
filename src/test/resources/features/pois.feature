Feature: Obtener el punto más cercano a mi ubicación
  Dado tener que caminar poco
  Como usuario
  Quiero obtener el punto de interes más cercano a mi ubicación actual

  Background:
    Given que existen los puntos de interes
      | latitud   | longitud   | nombre        | descripcion                |
      | -34.59762 | -58.385527 | El cuartito   | Pizza con Faina increible  |
      | -34.60393 | -58.38605  | Guerrin       | Pizzeria al paso           |
      | -34.60370 | -58.37905  | Las cuartetas | La mejor pizza a la piedra |

  Scenario: Obtener el punto más cercano a mi ubicación
    Given que estoy en '-34.603765' y '-58.381570'
    When pido el POI mas cercano
    Then recibe la respuesta con codigo de estado 200 y contenido
    """
        {
            "latitud": -34.60370,
            "longitud": -58.37905,
            "nombre": "Las cuartetas",
            "descripcion": "La mejor pizza a la piedra"
        }
    """