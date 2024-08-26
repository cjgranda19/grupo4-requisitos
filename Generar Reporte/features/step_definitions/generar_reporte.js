const { Given, When, Then } = require('@cucumber/cucumber');
const assert = require('assert');

// Scenario: Generar reporte diario
Given('El administrador ha iniciado sesión en el sistema', function () {
    console.log('El administrador ha iniciado sesión en el sistema');
});

When('El administrador selecciona la opción de generar un reporte diario', function () {
    console.log('El administrador selecciona la opción de generar un reporte diario');
});

When('El administrador especifica una fecha válida con datos', function () {
    console.log('El administrador especifica una fecha válida con datos');
});

Then('El sistema genera y muestra el reporte de ventas diarias', function () {
    console.log('El sistema genera y muestra el reporte de ventas diarias');
});

// Scenario: Generar reporte mensual
When('El administrador selecciona un mes y año válidos con datos', function () {
    console.log('El administrador selecciona un mes y año válidos con datos');
});

When('El administrador selecciona la opción de generar un reporte mensual', function () {
    console.log('El administrador selecciona la opción de generar un reporte mensual');
});

Then('El sistema genera y muestra el reporte de ventas mensuales', function () {
    console.log('El sistema genera y muestra el reporte de ventas mensuales');
});

// Scenario: Generar reporte anual
When('El administrador especifica un año válido con datos', function () {
    console.log('El administrador especifica un año válido con datos');
});

When('El administrador selecciona la opción de generar un reporte anual', function () {
    console.log('El administrador selecciona la opción de generar un reporte anual');
});

Then('El sistema genera y muestra el reporte de ventas anuales', function () {
    console.log('El sistema genera y muestra el reporte de ventas anuales');
});

// Scenario: Generar reporte sin datos
When('El administrador selecciona un rango de fechas sin datos de ventas', function () {
    console.log('El administrador selecciona un rango de fechas sin datos de ventas');
});

Then('El sistema muestra un mensaje indicando que no hay datos para el rango seleccionado', function () {
    console.log('El sistema muestra un mensaje indicando que no hay datos para el rango seleccionado');
});