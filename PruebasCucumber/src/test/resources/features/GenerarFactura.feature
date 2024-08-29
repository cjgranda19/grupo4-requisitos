Feature: Generar Factura
  Como cajero
  Quiero generar una factura detallada para el cliente
  Para cumplir con las normativas fiscales y asegurar la entrega correcta

  Scenario Outline: Validar que se genere correctamente una factura para el cliente cuando el SRI está disponible
    Given El cliente ha realizado una compra y el sistema SRI está "disponible"
    When Confirmo que la compra es correcta y el sistema genera la factura con el nombre "<nombre>", el correo "<correo>", la cantidad "<cantidad>" y el monto "<monto>"
    Then Se debe validar que la factura se haya "generado correctamente" y se haya "enviado" al cliente

    Examples:
      | nombre      | correo                 | cantidad | monto  |
      | Juan Pérez  | juan.perez@example.com | 5        | 25.00  |

  Scenario Outline: Validar que la factura se ponga en cola cuando el SRI no está disponible
    Given El cliente ha realizado una compra y el sistema SRI está "no disponible"
    When Confirmo que la compra es correcta y el sistema genera la factura con el nombre "<nombre>", el correo "<correo>", la cantidad "<cantidad>" y el monto "<monto>"
    Then Se debe validar que la factura se haya "puesto en cola" y se haya "guardado para procesamiento posterior" al cliente

    Examples:
      | nombre        | correo                   | cantidad | monto  |
      | Carlos Ruiz   | carlos.ruiz@example.com  | 2        | 15.00  |
