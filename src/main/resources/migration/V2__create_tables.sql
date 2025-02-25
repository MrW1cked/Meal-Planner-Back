-- =============================================
-- TABELA 1: Meals
-- =============================================
CREATE TABLE meals (
                       id INT PRIMARY KEY,
                       date DATE NOT NULL,
                       dayOfWeek VARCHAR(50) NOT NULL,
                       mealType VARCHAR(50) NOT NULL,
                       itemType VARCHAR(255) NOT NULL,
                       itemName VARCHAR(255),
                       itemColour VARCHAR(50),
                       itemPrice DECIMAL(10, 2)
);

-- =============================================
-- TABELA 2: Pantry (Dispensa)
-- =============================================
CREATE TABLE pantry (
                        id INT PRIMARY KEY,
                        itemType VARCHAR(255),
                        itemName VARCHAR(255),
                        itemColour VARCHAR(50),
                        itemPricePerDosis DECIMAL(10, 2),
                        itemTotalDosis INT
);

-- =============================================
-- TABELA 3: ShoppingList (Lista de Compras)
-- =============================================
CREATE TABLE shopping_list (
                               id INT PRIMARY KEY,
                               itemType VARCHAR(255),
                               itemName VARCHAR(255),
                               itemColour VARCHAR(50)
);

-- =============================================
-- SEQUENCIAS: meals_seq, pantry_seq, shopping_list_seq
-- =============================================
-- Para a tabela "meals"
CREATE SEQUENCE meals_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Para a tabela "pantry"
CREATE SEQUENCE pantry_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Para a tabela "shopping_list"
CREATE SEQUENCE shopping_list_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;