Feature: Revisar factura
  Como cliente de la panadería "La Italiana"
  Quiero revisar mis facturas
  Para verificar los detalles de mis compras

  Scenario: Visualizar factura existente
    Given el cliente ha iniciado sesión en su cuenta
    When el cliente selecciona una factura para revisar
    Then el sistema muestra los detalles completos de la factura

  Scenario: Descargar factura
    Given el cliente está revisando una factura
    When el cliente hace clic en el botón "Descargar Factura"
    Then el sistema inicia la descarga de la factura en formato PDF

  Scenario: Imprimir factura
    Given el cliente está revisando una factura
    When el cliente hace clic en el botón "Imprimir Factura"
    Then el sistema abre el diálogo de impresión del navegador