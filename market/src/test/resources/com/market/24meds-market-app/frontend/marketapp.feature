Feature: Market App

Scenario: Making an order
    When a costumer wants to buy a product
        And he is on the main page
        And sees a product we wants
        And he adds it to the cart
    Then he clicks on the "Card" button
        And clicks on the "Make order" button to conclude his order
