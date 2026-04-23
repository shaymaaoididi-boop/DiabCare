System.out.println("--- Dashboard Médecin (Yassmine) ---");

Patient p1 = new Patient("Khalil", "k@mail.com", "123");
p1.setDerniereMesure(18.5);

Patient p2 = new Patient("Amira", "a@mail.com", "123");
p2.setDerniereMesure(7.2);

Patient p3 = new Patient("Leila", "l@mail.com", "123");
p3.setDerniereMesure(14.0);

Medecin med = new Medecin("Dr. Yassmine");

med.ajouterPatient(p1);
med.ajouterPatient(p2);
med.ajouterPatient(p3);

med.afficherDashboard();