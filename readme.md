# KlausurHelfer
Jetzt mit instant copy Funktion.

## How to use
1. KlausurHelfer in die lokale IDE kopieren/importieren und starten (Rechtsklick -> Run as -> Java Application)
2. �ber die Tabs zum ben�tigten Element navigieren und mit der Maus drauf zeigen (nicht klicken)
3. Warten bis die Nachricht "copied <Element>" kommt und wieder verschwindet
4. An die Stelle gehen wo das Element ben�tigt wird und einf�gen (Strg+C)

## Contents
#### IO
Beim Kopieren (Nach Punkt How to use) des Eingabefensters oder des Knopfes "Zur Datei hinzuf�gen" wird eine Methode zum Schreiben eines Strings in eine Datei in die Zwischenablage Kopiert
Beim Kopieren des "Inhalt der Datei "myDir/foo" Labels oder des Datei-Inhalt-Ausgabefensters wird eine Methode zum Auslesen von Dateien kopiert
#### COMPONENTS
Hier wird die Deklaration und Initialisierung der entsprechenden Swing Komponente kopiert
Bei den RadioButtons wird direkt eine ButtonGroup mitdefiniert, welche f�r RadioButtons n�tig ist
#### LAYOUTS
Hier wird das entsprechende Layout kopiert und eine Beispielkomponente f�r jede Art wie man in diesem Layout Komponenten hinzuf�gen kann
#### EVENTS
Hier werden auf 3 Arten implementierte ActionListener kopiert
1. Lambda: Empfohlen. Implementieren des ActionListener Interfaces �ber eine anonyme Funktion
2. Subclass: Implementieren des ActionListener Interfaces �ber eine Subklasse
3. Inner Anonymous: Implementieren des ActionListener Interfaces �ber eine innere anonyme Klasse
#### THREADS
Kopiert einen Thread mit einer Lambda-Funktion als Runnable. F�r andere Implementierungen eines Runnables, verwende die Vorlagen vom Events Tab
#### PAINT
Kopiert ein JPanel dessen paint Funktion �berschrieben wurde, mitsamt einigen grundlegenden Methoden zum zeichnen
#### DATASTRUCT
Keine Kopierfunktion.

## Empfehlung:
Erstmal Zuhause vor der Pr�fung ein bisschen mit dem Klausurhelfer rumspielen, um sich grob einen �berblick zu verschaffen.