## JavaTempCollector

### krótki opis

System "Temp Service" służy do rejestracji temperatury w miastach i pokazywaniu na tej podstawie statystyki pomiarów

### Wykorzystane technologie

Java, SpringBoot, Spring MVC, SpringWeb, JSP

### Dokładniejszy opis

Zbudowanie systemu w technologii SpringBoot do rejestracji i
wyświetlania pomiarów temperatury. Wykorzystanie odpowiednich struktur dla przechowywania danych 
zbudowanych w pamięci bez korzystania z bazy danych.

System posiada dwie formatki. Pierwsza formatkę do wprowadzenia nazwy miasta i temperatury
i zapisuje do struktur danych. Data wprowadzenia temperatury jest
zapisywana automatycznie.

Druga formatka wyświetla nazwy miast (posortowane alfabetycznie) (jeśli
nazwa miasta nie była wcześniej zapisana należy ją dodać do listy) i
podaje temperatury: średnią dobową, średnią nocną (od godz. 0:00 do 5:00
i od 20:00 do 24:00) i średnią dzienną (od 6:00 do 19:00) dla każdego
miasta z osobna i dla całego kraju z danego dnia.
Temperaturę zaokrąglamy do pełnych stopni.

### Opis formatek

W katalogu 'screenshots/' znajdują się dwa zrzuty ekranu,
powiązane z dwoma stronami JSP.
Obraz 'screen_of_enter.png' odnosi się do 'webapp/WEB-INF/jsp/enter.jsp'.
Obraz 'screen_of_list.png' odnosi się do 'webapp/WEB-INF/jsp/list.jsp'.