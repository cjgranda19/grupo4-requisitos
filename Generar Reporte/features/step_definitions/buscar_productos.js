const { Given, When, Then } = require('@cucumber/cucumber');
const assert = require('assert');

// Scenario: Busqueda de productos por termino
Given('El cliente está en la página de búsqueda de productos', function () {
    console.log('El cliente está en la página de búsqueda de productos');
});

When('El cliente inicia la búsqueda', function () {
    console.log('El cliente inicia la búsqueda');
});

When('El cliente ingresa un término de búsqueda válido', function () {
    console.log('El cliente ingresa un término de búsqueda válido');
});

Then('El sistema muestra una lista de productos que coinciden con el término de búsqueda', function () {
    console.log('El sistema muestra una lista de productos que coinciden con el término de búsqueda');
});

When('El cliente selecciona una categoría válida', function () {
    console.log('El cliente selecciona una categoría válida');
});

Then('El sistema muestra una lista de productos que pertenecen a la categoría seleccionada', function () {
    console.log('El sistema muestra una lista de productos que pertenecen a la categoría seleccionada');
});

When('El cliente ingresa un término de búsqueda que no tiene coincidencias', function () {
    console.log('El cliente ingresa un término de búsqueda que no tiene coincidencias');
});

Then('El sistema muestra un mensaje indicando que no se encontraron productos', function () {
    console.log('El sistema muestra un mensaje indicando que no se encontraron productos');
});

When('El cliente selecciona una categoría inexistente', function () {
    console.log('El cliente selecciona una categoría inexistente');
});

Then('El sistema muestra un mensaje de error indicando que la categoría no es válida', function () {
    console.log('El sistema muestra un mensaje de error indicando que la categoría no es válida');
});