# Progetto Spring Batch

Questo progetto dimostra l'implementazione di un'applicazione batch utilizzando Spring Batch per leggere dati da un servizio REST, elaborarli e scriverli su un database.

## Descrizione

L'applicazione batch esegue i seguenti passaggi:

1. **Lettura dei dati**: I dati vengono letti da un servizio REST che fornisce informazioni sugli utenti.
2. **Elaborazione dei dati**: Ogni utente viene elaborato, convertendo i nomi in maiuscolo.
3. **Scrittura dei dati**: Gli utenti elaborati vengono scritti in un database relazionale.
4. **Listener di Fine Lavoro**: Al termine del lavoro, un listener personalizzato controlla lo stato del lavoro. Se il lavoro è stato completato con successo, il programma termina.

## Struttura del Progetto

- **engine**: Contiene la configurazione principale di Spring Batch.
- **listener**: Contiene un listener personalizzato per il lavoro Spring Batch.
- **model**: Contiene le classi di modello per gli oggetti utilizzati nel processo di batch.
- **processor**: Contiene la logica di business per processare gli oggetti durante il lavoro batch.
- **reader**: Contiene la logica per leggere i dati da una sorgente esterna durante il lavoro batch.
- **writer**: Contiene la logica per scrivere i dati su una destinazione durante il lavoro batch.
- **application.properties**: Contiene la configurazione dell'applicazione Spring.

## Dipendenze

- Spring Batch
- Spring JDBC
- Spring Web
- Lombok
- H2 Database(essenziale per le tabelle di appoggio del JobRepository)
- RestTemplate

## Configurazione

La configurazione principale dell'applicazione si trova in `BatchConfig.java` all'interno del package `engine`. Qui vengono definiti i bean per il lettore, il processore, lo scrittore e il job stesso.

## Come Eseguire

Per eseguire l'applicazione, è sufficiente avviare la classe `BatchApplication.java`.

## Proprietà dell'Applicazione

Le proprietà dell'applicazione sono configurate nel file `application.properties`. Qui è possibile modificare la configurazione del datasource e altre proprietà dell'applicazione.

## Personalizzazione

- È possibile modificare la logica di lettura, elaborazione e scrittura nel lettore, nel processore e nello scrittore rispettivamente per adattarsi alle esigenze specifiche dell'applicazione.
- È possibile estendere o modificare il listener del lavoro (`CustomJobListener`) per aggiungere ulteriori comportamenti o gestire eventi specifici del lavoro.
