

# Endpointy

## Store 

Scieżka: `localhost:8080/api/store/`

### 1. **Sprawdzenie wszystkich produktów**

#### **GET** `/`


### 2. **Dodanie produktu**
#### **POST** `/add`


#### **Body żądania:**
Wymagane jest przesłanie obiektu JSON w poniższym formacie:

```json
{
    "name": "Product", 
    "normalPrice": 14,
    "requiredQuantity": 3,
    "specialPrice": 10
}
```

Pola `requiredQuantity` i `specialPrice` są opcjonalne. W przypadku ich braku zostaną ustawione na `null`.

## Checkout

Ścieżka: `localhost:8080/api/checkout/`

### 1. **Dodanie do koszyka**

#### **POST** `/add_to_cart`

#### **Body żądania:**
Wymagane jest przesłanie obiektu JSON w poniższym formacie:

```json
{
    "itemId": 5,
    "quantity": 10
}
```

### 2. **Wyświetlenie koszyka**

#### **GET** `/show_cart`

### 3. **Pobranie ceny produktu**

#### **GET** `/get_price/{id}`

### 4. **Pobranie paragonu**

#### **GET** `/get_receipt`


## Discounts

Ścieżka: `localhost:8080/api/discounts/`

### 1. **Dodanie zniżki**

#### **POST** `/add`

#### **Body żądania:**
Wymagane jest przesłanie obiektu JSON w poniższym formacie:

```json
{
    "item1Id": 1,
    "item2Id": 2,
    "value": 5
}
```

### 2. **Wyświetlenie wszystkich zniżek**

#### **GET** `/show_all`