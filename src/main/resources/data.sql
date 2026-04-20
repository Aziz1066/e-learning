-- =====================================================
-- PLATEFORME E-LEARNING - DONNÉES DE TEST
-- AVEC IGNORE POUR ÉVITER LES DOUBLONS
-- =====================================================

-- -----------------------------------------------------
-- 1. UTILISATEURS (INSERT IGNORE pour éviter les doublons)
-- -----------------------------------------------------
INSERT IGNORE INTO utilisateur (nom, email, mot_de_passe, role, biographie, niveau_etude, date_inscription) VALUES 
('Jean Dupont', 'jean.formateur@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'FORMATEUR', 'Professeur d anglais avec 10 ans d expérience', NULL, NOW()),
('Sophie Martin', 'sophie.formateur@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'FORMATEUR', 'Professeure de mathématiques passionnée', NULL, NOW()),
('Pierre Durand', 'pierre.formateur@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'FORMATEUR', 'Docteur en biologie', NULL, NOW()),
('Alice Lefebvre', 'alice.apprenant@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'APPRENANT', NULL, 'Licence', NOW()),
('Thomas Bernard', 'thomas.apprenant@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'APPRENANT', NULL, 'Master', NOW()),
('Admin System', 'admin@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'ADMIN', 'Administrateur de la plateforme', NULL, NOW());

-- -----------------------------------------------------
-- 2. COURS (3 cours : Anglais, Maths, SVT)
-- -----------------------------------------------------
INSERT IGNORE INTO cours (id, titre, description, categorie, niveau, image_url, actif, formateur_id, date_creation) VALUES
(1, 'Anglais niveau A1', 'Apprenez les bases de l anglais : grammaire, vocabulaire et conversation.', 'Langues', 'DEBUTANT', 'anglais.jpg', 1, 1, NOW()),
(2, 'Mathématiques - Analyse', 'Maîtrisez les concepts fondamentaux de l analyse mathématique.', 'Sciences', 'INTERMEDIAIRE', 'maths.jpg', 1, 2, NOW()),
(3, 'SVT - Biologie cellulaire', 'Découvrez la structure et le fonctionnement de la cellule vivante.', 'Sciences', 'DEBUTANT', 'svt.jpg', 1, 3, NOW());

-- -----------------------------------------------------
-- 3. MODULES (2 modules par cours)
-- -----------------------------------------------------
INSERT IGNORE INTO module (id, titre, description, ordre, cours_id) VALUES
(1, 'Module 1 : Grammaire de base', 'Les bases de la grammaire anglaise', 1, 1),
(2, 'Module 2 : Vocabulaire essentiel', 'Les mots et expressions à connaître', 2, 1),
(3, 'Module 1 : Les fonctions', 'Introduction aux fonctions mathématiques', 1, 2),
(4, 'Module 2 : Limites et continuité', 'Étude des limites d une fonction', 2, 2),
(5, 'Module 1 : La cellule', 'Structure et organisation de la cellule', 1, 3),
(6, 'Module 2 : Les organites', 'Rôle des différents organites cellulaires', 2, 3);

-- -----------------------------------------------------
-- 4. LEÇONS (3 leçons par module)
-- -----------------------------------------------------
INSERT IGNORE INTO lecon (id, titre, contenu, ordre, duree_min, module_id) VALUES
-- Module 1 : Anglais - Grammaire
(1, 'Leçon 1 : Le verbe être (to be)', 'Le verbe "to be" est le verbe le plus important en anglais.', 1, 15, 1),
(2, 'Leçon 2 : Les articles (a/an/the)', 'Les articles définis et indéfinis.', 2, 12, 1),
(3, 'Leçon 3 : Les pronoms personnels', 'I, you, he, she, it, we, you, they.', 3, 10, 1),
-- Module 2 : Anglais - Vocabulaire
(4, 'Leçon 1 : Les salutations', 'Hello, Hi, Good morning...', 1, 10, 2),
(5, 'Leçon 2 : Les nombres', 'One, two, three...', 2, 15, 2),
(6, 'Leçon 3 : La famille', 'Mother, father, sister...', 3, 12, 2),
-- Module 3 : Maths - Fonctions
(7, 'Leçon 1 : Définition d une fonction', 'Une fonction associe à chaque x un unique f(x).', 1, 15, 3),
(8, 'Leçon 2 : Représentation graphique', 'La courbe représentative.', 2, 20, 3),
(9, 'Leçon 3 : Fonctions affines', 'f(x) = ax + b', 3, 15, 3),
-- Module 4 : Maths - Limites
(10, 'Leçon 1 : Introduction aux limites', 'La notion de limite.', 1, 20, 4),
(11, 'Leçon 2 : Limites infinies', 'Quand f(x) devient très grand.', 2, 18, 4),
(12, 'Leçon 3 : Limites en l infini', 'Quand x tend vers +∞.', 3, 18, 4),
-- Module 5 : SVT - La cellule
(13, 'Leçon 1 : La théorie cellulaire', 'La cellule est l unité du vivant.', 1, 15, 5),
(14, 'Leçon 2 : La cellule animale', 'Structure de la cellule animale.', 2, 18, 5),
(15, 'Leçon 3 : La cellule végétale', 'Spécificités de la cellule végétale.', 3, 18, 5),
-- Module 6 : SVT - Les organites
(16, 'Leçon 1 : Le noyau', 'Le noyau contient l ADN.', 1, 15, 6),
(17, 'Leçon 2 : Les mitochondries', 'Centrales énergétiques.', 2, 15, 6),
(18, 'Leçon 3 : Les ribosomes', 'Synthèse des protéines.', 3, 12, 6);

-- -----------------------------------------------------
-- 5. INSCRIPTIONS
-- -----------------------------------------------------
INSERT IGNORE INTO inscription (apprenant_id, cours_id, date_inscription, statut) VALUES
(4, 1, NOW(), 'EN_COURS'),
(4, 2, NOW(), 'EN_COURS'),
(5, 1, NOW(), 'EN_COURS'),
(5, 3, NOW(), 'EN_COURS');

-- -----------------------------------------------------
-- 6. QUIZ (1 quiz par module)
-- -----------------------------------------------------
INSERT IGNORE INTO quiz (id, titre, description, score_minimum, module_id) VALUES
(1, 'Quiz Grammaire anglaise', 'Testez vos connaissances en grammaire anglaise', 3, 1),
(2, 'Quiz Vocabulaire anglais', 'Testez votre vocabulaire anglais', 3, 2),
(3, 'Quiz Fonctions', 'Testez vos connaissances sur les fonctions', 3, 3),
(4, 'Quiz Limites', 'Testez vos connaissances sur les limites', 3, 4),
(5, 'Quiz Biologie cellulaire', 'Testez vos connaissances sur la cellule', 3, 5),
(6, 'Quiz Organites', 'Testez vos connaissances sur les organites', 3, 6);

-- -----------------------------------------------------
-- 7. QUESTIONS (5 questions par quiz)
-- -----------------------------------------------------
INSERT IGNORE INTO question (id, texte, choix1, choix2, choix3, choix4, bonne_reponse, points, quiz_id) VALUES
-- Quiz 1
(1, 'Comment dit-on "je suis" en anglais ?', 'I are', 'I am', 'I is', 'I be', 2, 1, 1),
(2, 'Quel est l article indéfini pour "apple" ?', 'a', 'an', 'the', 'apple', 2, 1, 1),
(3, 'Quel est le pronom personnel pour "Marie" ?', 'He', 'It', 'She', 'They', 3, 1, 1),
(4, 'Comment dit-on "nous sommes" ?', 'We are', 'We is', 'We am', 'We be', 1, 1, 1),
(5, 'Que signifie "they are" ?', 'Il/elle est', 'Nous sommes', 'Vous êtes', 'Ils/elles sont', 4, 1, 1),
-- Quiz 2
(6, 'Que signifie "Hello" ?', 'Au revoir', 'Bonjour', 'Bonsoir', 'Merci', 2, 1, 2),
(7, 'Comment dit-on "merci" en anglais ?', 'Please', 'Sorry', 'Thank you', 'You re welcome', 3, 1, 2),
(8, 'Que signifie "mother" ?', 'Père', 'Frère', 'Sœur', 'Mère', 4, 1, 2),
(9, 'Comment dit-on "cinq" ?', 'Five', 'Four', 'Six', 'Seven', 1, 1, 2),
(10, 'Que signifie "Good night" ?', 'Bonjour', 'Bonne après-midi', 'Bonne nuit', 'Bonne soirée', 3, 1, 2),
-- Quiz 3
(11, 'Une fonction f associe à chaque x :', 'Deux images', 'Une seule image', 'Aucune image', 'Plusieurs images', 2, 1, 3),
(12, 'La représentation graphique est dans :', 'Un repère', 'Un tableau', 'Une liste', 'Un texte', 1, 1, 3),
(13, 'Une fonction affine est de la forme :', 'f(x) = ax²', 'f(x) = ax + b', 'f(x) = a/x', 'f(x) = √x', 2, 1, 3),
(14, 'Que représente "a" dans f(x) = ax + b ?', 'Ordonnée origine', 'Coefficient directeur', 'Constante', 'Variable', 2, 1, 3),
(15, 'Que représente "b" dans f(x) = ax + b ?', 'Ordonnée origine', 'Coefficient directeur', 'Pente', 'Variable', 1, 1, 3),
-- Quiz 4
(16, 'Que signifie "limite infinie" ?', 'Fonction constante', 'Fonction tend vers 0', 'Fonction devient très grande', 'Fonction périodique', 3, 1, 4),
(17, 'Quand x tend vers +∞, 1/x tend vers :', '+∞', '-∞', '0', '1', 3, 1, 4),
(18, 'Quand x tend vers 0, 1/x tend vers :', '0', '1', 'L infini', '-1', 3, 1, 4),
(19, 'La limite de x² quand x tend vers +∞ est :', '0', '1', '+∞', '-∞', 3, 1, 4),
(20, 'Une fonction peut-elle avoir une limite finie ?', 'Oui', 'Non', 'Jamais', 'Parfois', 1, 1, 4),
-- Quiz 5
(21, 'Qui a formulé la théorie cellulaire ?', 'Pasteur', 'Darwin', 'Schleiden et Schwann', 'Lamarck', 3, 1, 5),
(22, 'La cellule est :', 'Unité du vivant', 'Un organe', 'Un tissu', 'Un système', 1, 1, 5),
(23, 'La membrane cellulaire est :', 'Rigide', 'Perméable', 'Semi-perméable', 'Imperméable', 3, 1, 5),
(24, 'Où se trouve l ADN ?', 'Cytoplasme', 'Mitochondrie', 'Noyau', 'Membrane', 3, 1, 5),
(25, 'Quelle cellule a une paroi ?', 'Animale', 'Bactérienne', 'Humaine', 'Végétale', 4, 1, 5),
-- Quiz 6
(26, 'Le noyau contient :', 'ARN', 'ADN', 'Protéines', 'Lipides', 2, 1, 6),
(27, 'Les mitochondries produisent :', 'Glucose', 'ATP', 'CO2', 'Oxygène', 2, 1, 6),
(28, 'Les ribosomes synthétisent :', 'Lipides', 'Glucides', 'Protéines', 'Acides nucléiques', 3, 1, 6),
(29, 'Quel organite est présent dans la cellule végétale ?', 'Centriole', 'Lysosome', 'Chloroplaste', 'Flagelle', 3, 1, 6),
(30, 'La vacuole est :', 'Petite', 'Absente', 'Grande chez la plante', 'Rigide', 3, 1, 6);