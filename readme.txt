Projekt IJA 2018

Autoři:
Jan Rajnoha - xrajno09
Martin Zedníček - xzedni12

zakladne akcie aplikacie:

compile - kontrola vytvorenej schemy, detekcia cyklov
run - vypocita schemu
do step - spocita jeden krok schemy - aktualny blok je vyznaceny cervenym ramcekom

Ovládání:
- Nejprve vytvořte nové schéma o požadovaných rozměrech
- Pravým kliknutím na blochu vyberte libovolný blok
- Nastavte parametry bloku a jeho vstupy a výstupy. Vstupy a výstupy se doplňují podle toho, jaké bloky používáte
- Schéma spustíte tlačítkem Run a trasuje tlačítkem Trace
- Výsledek obou operací se zobrazí vedle tlačítek

- Tlačítky Load a Save provádíte načítání a ukládání schématu

Datové typy:
- Double - Základní datový typ.
- Point - Bod, který lze vytvořit spojením dvou Double hodnot
- Vector - Vektor je možné vytvořit spojeníém dvou Point hodnot

Bloky:
- Add - Sečte dvě Double hodnoty a vrátí Double
- Sub - Odečte dvě Double hodnoty a vrátí Double
- Mul - Vynásobí dvě Double hodnoty  a vrátí Double
- Div - Vydělí dvě Double hodnoty a vrátí Double
- Point - Vezme dvě Double hodnoty a vytvoří z nich Point
- Distance - Vezme dvě Point hodnoty a vypočte vzdálenost mezi nimi. Vrací Double
- Vector - Vezme dvě Point hdonoty a vrátí hodnotu typu Vector
- End bloky - Slouží k ukončení schématu a dělí se na Double, Point a Vector
- Start - Počátek schématu typu Double

Export/Import
Program je schopen exportovat schéma v podobě XML a zpátky jej načíst



