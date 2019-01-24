# KlausurHelfer
Jetzt mit instant copy Funktion.

## How to use
1. KlausurHelfer in die lokale IDE kopieren/importieren und starten (Rechtsklick -> Run as -> Java Application)
2. Über die Tabs zum benötigten Element navigieren und mit der Maus drauf zeigen (nicht klicken)
3. Warten bis die Nachricht `copied <Element>` kommt und wieder verschwindet
4. An die Stelle gehen wo das Element benötigt wird und einfügen (Strg+V)

## Contents
#### IO
Beim Kopieren (Nach Punkt How to use) des Eingabefensters oder des Knopfes `Zur Datei hinzufügen` wird eine Methode zum Schreiben eines Strings in eine Datei in die Zwischenablage Kopiert

Beim Kopieren des `Inhalt der Datei "myDir/foo` Labels oder des Datei-Inhalt-Ausgabefensters wird eine Methode zum Auslesen von Dateien kopiert
#### COMPONENTS
Hier wird die Deklaration und Initialisierung der entsprechenden Swing Komponente kopiert

Bei den RadioButtons wird direkt eine ButtonGroup mitdefiniert, welche für RadioButtons nötig ist

Für JFrame und Dialogs: Der Button der die Preview aufruft enthält hier den Kopiertext, nicht die preview Fenster selbst
#### LAYOUTS
Hier wird das entsprechende Layout kopiert und eine Beispielkomponente für jede Art wie man in diesem Layout Komponenten hinzufügen kann
#### EVENTS
Hier werden auf 3 Arten implementierte ActionListener kopiert
1. Lambda: Empfohlen. Implementieren des ActionListener Interfaces über eine anonyme Funktion
2. Subclass: Implementieren des ActionListener Interfaces über eine Subklasse
3. Inner Anonymous: Implementieren des ActionListener Interfaces über eine innere anonyme Klasse
#### THREADS
Kopiert einen Thread mit einer Lambda-Funktion als Runnable. Für andere Implementierungen eines Runnables, verwende die Vorlagen vom Events Tab
#### PAINT
Kopiert ein JPanel dessen paint Funktion überschrieben wurde, mitsamt einigen grundlegenden Methoden zum zeichnen
#### DATASTRUCT
Keine Kopierfunktion.

## Empfehlung:
Erstmal Zuhause vor der Prüfung ein bisschen mit dem Klausurhelfer rumspielen, um sich grob einen Überblick zu verschaffen.
