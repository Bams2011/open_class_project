pere(mamadou, coumba).
pere(mamadou, seynabou).
pere(pape_demba, mamadou).
pere(pere_demba, fatima).
pere(pathe, pape_demba).
pere(souleye, maimouna).
pere(souleye, khady).
pere(elimane, souleye).
pere(goorgui, safietou).
pere(goorgui, doro).
mere(maimouna, coumba).
mere(maimouna, seynabou).
mere(assane, mamadou).
mere(assane, fatima).
mere(ndeye, maimouna).
mere(ndeye, khady).
mere(khady, safietou).
mere(khady, doro).
parent(X,Y):- pere(X,Y).
parent(X,Y):-mere(X,Y).
frere_soeur(X, Y) :- parent(M,X), parent(M,Y),\+ X=Y.
grandpere(G,C) :- pere(F,C),pere(G,F).