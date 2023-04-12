# :pizza:Cibuļskis_Pica

## Mērķi
- [x] Iespējams pasūtīt picu vai uzbūvēt pašam
- [x] Ieviest maršrutu uz piegādes
- [ ] Salabot cepšanos ja picu skaits pārsniedz 3
- [ ] Salabot tekstu zem piegādes maršruta
- [ ] Optimizēt kodu
- [ ] Noņemt liekos libraries un kodu

## :information_source:Apraksts
Šī ir picu pasūtīšanas un veidošanas aplikācija, kura veidota ar Swing framework valodā Java.


## Gatavās picas
Šajā aplikācijā vari izvēleties starp 6 jau gatavām picām, kuras vari pasūtīt!
<pre><code>Šī loga realizēšanai tika izmantots JTabbenPane izkārtojums, daudzi JButtons un JLabels priekš pogām un teksta.</code></pre>
![image](https://user-images.githubusercontent.com/118617121/231410884-80c92e94-8365-4980-904a-954e90015f98.png)

Kad izvēlies kādu picu, tev dota iespēja izvēlēties picas diametru, mīklas biezumu un picu skaitu!

<pre><code>Lai realizētu šada tipa logu vajadzīgi ir vairāki JToggleButtons un JLabel kuram kā ikona ir iestatīta picas bilde.</code></pre>

![image](https://user-images.githubusercontent.com/118617121/231411701-0bd7eb9b-57f0-4cb2-9822-3b9684164b63.png)

## Būvē savu picu
Kad izvēlies logu *Būvē savu picu* tev tiek dota izvēleties pašam ko liec uz picas.
<pre><code>Šī loga realizēšanai tika izmantoti ļoti daudz JToggleButtons, kāds JLabel, JButton un JSpinner.</code></pre>
![image](https://user-images.githubusercontent.com/118617121/231412886-2226f087-1eb9-4010-8b30-55624361d815.png)
## Vizualizācija
Kāmēr būvē savu picu tās apraksts, cena un izskats atjauninās uz katra pogas spiediena.
Šeit redzama 20 cm diametra pica ar plānu mīklu, tomātu mērci, bez siera ar ananāsiem un vistu!:yum:
<pre><code>Lai realizētu šada tipa vizuālu picas būvēšanu nepieciešami ļoti daudz JLabels kuriem ikona ir iestatīta kā noteiktā picas <br>
sastāvdaļa kas saistīta ar kādu JToggleButton uz ekrāna .</code></pre>
![image](https://user-images.githubusercontent.com/118617121/231428156-c4f1976b-51af-40d4-9655-8e57b8377170.png)
## Grozs:shopping_cart:
Groza logā var redzēt visus pasūtījumus kuri ievietoti tavā grozā.
Pasūtījumus var noņemt tiem uzspiežot virsū sarakstā.
Šajā logā arī iespējams norādīt vai picu sūtīsi uz mājām vai ēdīsi uz vietas, kā arī iespējams norādīt adresi.

<pre><code>Lai realizētu šadu sarakstu es izmantoju ListCellRenderer, kurš sastāv no JLabel ar ikonu, JLabel ar aprakstu un nonemsanas pogu. 
Priekš ievades tika lietots editable JComboBox kurš saglabā savu vēsturi un vines JButton priekš pasūtīšanas.</code></pre>
![image](https://user-images.githubusercontent.com/118617121/231429651-4953d0d4-b22f-41cb-9549-aac53c404584.png)

## Piegādes vizualizācija
Šeit var redzēt kurjera maršrutu uz ievadīto adresi!
<pre><code>Lai realizētu piegādes vizualizāciju es izmantoju MapQuesta shortest route API, kurš arī atgriež distanci kilometros.</code></pre>
![image](https://user-images.githubusercontent.com/118617121/231431128-949f02d9-7331-4fb8-b15e-a9db59c23a34.png)


## Reģistrācija
Pilnajā reģistrācijas logā tev tiek jautāts ievadīt savu - vārdu, uzvārdu un numuru - kā arī izvēleties norēķināšanās veidu!
<pre><code>Šī realizēšanai tika pielietoti vairāki JInputFields, JRadioButtons un viena JButton.</code></pre>

![image](https://user-images.githubusercontent.com/118617121/231430137-c1998c53-3020-4ed5-bf53-a3334d08cd55.png)

## Daļēja reģistrācija
Daļējā reģistrācijas logā tev tiek jautāts ievadīt tikai savu vārdu un izvēlēties norēķināšanās veidu!
<pre><code>Šī realizēšanai tika pielietots viens JInputField, JRadioButtons un viena JButton.</code></pre>
![image](https://user-images.githubusercontent.com/118617121/231431486-04dca5c9-7542-49d8-bf9f-ce572dfeb176.png)


## Pasūtījuma progress
Šajā logā var redzēt cik procentus pasūtītās picas ir izcepušās!
<pre><code>Lai realizētu šādu procesa vizualizāciju es izmantoju 3 Threads kuros lēnām palielinājās JProgressBar vērtības,
kā arī JLabel procenti pēc picu ievietošanas pareizajos slotos.</code></pre>
![image](https://user-images.githubusercontent.com/118617121/231438298-60bce175-38ed-48dd-be01-263bd433248b.png)






