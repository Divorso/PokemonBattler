# PokeIndex

![Pokedex](images/pokedexHome.png)

PokeIndex är min första inlämningsuppgift på JENSEN Yrkeshögskolas kurs
**Programmering med Java, grund**.

Programmet är en konsolbaserad Pokedex där användaren kan skapa och hantera Pokemon.
Data sparas i en JSON-fil med hjälp av Jackson.

## Funktioner

I programmet kan användaren:

- Visa alla Pokemon
- Lägga till en Pokemon
- Ta bort en Pokemon
- Uppdatera en Pokemon
- Uppdatera, lägga till och ta bort attacker
- Spara Pokemon till JSON
- Ladda Pokemon från JSON
- Återställa Pokedex till seed data
- Avsluta programmet och spara automatiskt

En Pokemon kan ha mellan 1 och 4 attacker.

## Hur man kör programmet

Projektet är byggt med Java och Maven.

1. Klona eller ladda ner projektet.
2. Öppna projektet i IntelliJ IDEA.
3. Kontrollera att Maven dependencies har laddats.
4. Kör `Main.java`.
5. Programmet startar i konsolen.

## Användning

Navigera i huvudmenyn genom att skriva en siffra mellan 1-8 och trycka Enter.

Vissa menyval kräver ytterligare input. Om du till exempel vill ta bort en Pokemon
skriver du namnet på den Pokemon du vill ta bort.

Programmet validerar användarens input och frågar igen om ett ogiltigt värde anges.

## Data

Pokemon sparas i `Pokemons.json`.

Om det inte finns någon sparad Pokemon-data laddar programmet automatiskt in
fördefinierad seed data med 6 Pokemon.

## Tekniker

- Java
- Maven
- Jackson
- JSON
- ArrayList
- Enum
- Exceptions
- Egna Exceptions
- Filhantering
- Git och GitHub