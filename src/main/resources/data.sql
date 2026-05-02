-- =====================================================
-- PLATEFORME E-LEARNING - BASE DE DONNÉES COMPLÈTE
-- INCLUANT : LANGUES, SCIENCES ET CERTIFICATIONS IT
-- =====================================================

-- -----------------------------------------------------
-- 1. UTILISATEURS
-- -----------------------------------------------------
INSERT IGNORE INTO utilisateur (id, nom, email, mot_de_passe, role, biographie, niveau_etude, date_inscription) VALUES 
(1, 'Jean Dupont', 'jean.formateur@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'FORMATEUR', 'Professeur d anglais avec 10 ans d expérience', NULL, NOW()),
(2, 'Sophie Martin', 'sophie.formateur@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'FORMATEUR', 'Professeure de mathématiques passionnée', NULL, NOW()),
(3, 'Pierre Durand', 'pierre.formateur@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'FORMATEUR', 'Docteur en biologie', NULL, NOW()),
(4, 'Alice Lefebvre', 'alice.apprenant@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'APPRENANT', NULL, 'Licence', NOW()),
(5, 'Thomas Bernard', 'thomas.apprenant@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'APPRENANT', NULL, 'Master', NOW()),
(6, 'Admin System', 'admin@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'ADMIN', 'Administrateur de la plateforme', NULL, NOW()),
(10, 'Dr. Alan Turing', 'alan.c@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'FORMATEUR', 'Expert en C et systèmes embarqués', 'Doctorat', NOW()),
(11, 'Sarah Cisco', 'sarah.net@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'FORMATEUR', 'Ingénieure réseaux certifiée CCIE', 'Master', NOW()),
(12, 'Andrew Ng-Like', 'andrew.ml@elearning.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi', 'FORMATEUR', 'Chercheur en Intelligence Artificielle', 'Doctorat', NOW());

-- -----------------------------------------------------
-- 2. COURS
-- -----------------------------------------------------
INSERT IGNORE INTO cours (id, titre, description, categorie, niveau, image_url, actif, formateur_id, date_creation) VALUES
(1, 'Anglais niveau A1', 'Apprenez les bases de l anglais : grammaire, vocabulaire et conversation.', 'Langues', 'DEBUTANT', 'anglais.jpg', 1, 1, NOW()),
(2, 'Mathématiques - Analyse', 'Maîtrisez les concepts fondamentaux de l analyse mathématique.', 'Sciences', 'INTERMEDIAIRE', 'maths.jpg', 1, 2, NOW()),
(3, 'SVT - Biologie cellulaire', 'Découvrez la structure et le fonctionnement de la cellule vivante.', 'Sciences', 'DEBUTANT', 'svt.jpg', 1, 3, NOW()),
(4, 'Programmation C Avancée', 'Maîtrise de la gestion mémoire, des pointeurs et de l optimisation système.', 'Informatique', 'AVANCE', 'c_pro.jpg', 1, 10, NOW()),
(5, 'Réseaux & Protocoles (CCNA)', 'Architecture OSI, routage IP et commutation LAN.', 'Réseaux', 'INTERMEDIAIRE', 'cisco.jpg', 1, 11, NOW()),
(6, 'Fondamentaux du Machine Learning', 'Régression, Classification et réseaux de neurones avec Python.', 'Data Science', 'AVANCE', 'ml.jpg', 1, 12, NOW()),
(7, 'Mathématiques : Algèbre Linéaire', 'Espaces vectoriels et calcul matriciel pour la Data Science.', 'Sciences', 'INTERMEDIAIRE', 'maths_v2.jpg', 1, 2, NOW());

-- -----------------------------------------------------
-- 3. MODULES
-- -----------------------------------------------------
INSERT IGNORE INTO module (id, titre, description, ordre, cours_id) VALUES
(1, 'Module 1 : Grammaire de base', 'Les bases de la grammaire anglaise', 1, 1),
(2, 'Module 2 : Vocabulaire essentiel', 'Les mots et expressions à connaître', 2, 1),
(3, 'Module 1 : Les fonctions', 'Introduction aux fonctions mathématiques', 1, 2),
(4, 'Module 2 : Limites et continuité', 'Étude des limites d une fonction', 2, 2),
(5, 'Module 1 : La cellule', 'Structure et organisation de la cellule', 1, 3),
(6, 'Module 2 : Les organites', 'Rôle des différents organites cellulaires', 2, 3),
(10, 'Gestion Mémoire Dynamique', 'Allocation, libération et fuites mémoire.', 1, 4),
(11, 'Structures de Données Complexes', 'Listes chaînées, arbres et pointeurs de fonctions.', 2, 4),
(12, 'Modèle OSI et TCP/IP', 'Comprendre les couches de communication.', 1, 5),
(13, 'Routage et Commutation', 'Configuration des routeurs et switchs.', 2, 5),
(14, 'Apprentissage Supervisé', 'Régression linéaire et logistique.', 1, 6),
(15, 'Réseaux de Neurones', 'Introduction au Deep Learning.', 2, 6),
(16, 'Espaces Vectoriels', 'Bases, dimension et sous-espaces.', 1, 7),
(17, 'Matrices et Applications Linéaires', 'Calcul matriciel et transformations.', 2, 7);

-- -----------------------------------------------------
-- 4. LEÇONS
-- -----------------------------------------------------
INSERT IGNORE INTO lecon (id, titre, contenu, ordre, duree_min, module_id) VALUES
-- Anglais
(1, 'Leçon 1 : Le verbe être (to be)', 'Le verbe "to be" est le verbe le plus important en anglais.', 1, 15, 1),
(2, 'Leçon 2 : Les articles (a/an/the)', 'Les articles définis et indéfinis.', 2, 12, 1),
(3, 'Leçon 3 : Les pronoms personnels', 'I, you, he, she, it, we, you, they.', 3, 10, 1),
(4, 'Leçon 1 : Les salutations', 'Hello, Hi, Good morning...', 1, 10, 2),
-- Maths Analyse
(7, 'Leçon 1 : Définition d une fonction', 'Une fonction associe à chaque x un unique f(x).', 1, 15, 3),
(10, 'Leçon 1 : Introduction aux limites', 'La notion de limite.', 1, 20, 4),
-- C Avancé
(30, 'Malloc, Calloc et Realloc', 'Utilisation avancée du tas (Heap).', 1, 30, 10),
(31, 'Valgrind et Debugging', 'Détection des memory leaks.', 2, 25, 10),
(32, 'Les Pointeurs de Fonctions', 'Créer des callbacks en C.', 1, 40, 11),
-- Réseaux
(34, 'La Couche Transport (TCP/UDP)', 'Différences et cas d utilisation.', 1, 20, 12),
(35, 'Protocoles de Routage (OSPF)', 'Fonctionnement du routage dynamique.', 1, 35, 13),
-- Machine Learning
(40, 'La Descente de Gradient', 'Optimisation de la fonction de coût.', 1, 45, 14),
(41, 'Overfitting et Régularisation', 'Techniques L1/L2 et Dropout.', 2, 35, 14),
(42, 'Le Perceptron Multicouche', 'Architecture d un réseau simple.', 1, 50, 15),
-- Algèbre Linéaire
(43, 'Dépendance et Indépendance Linéaire', 'Notion de famille libre et liée.', 1, 30, 16),
(44, 'Inversion de Matrice', 'Méthode de Gauss-Jordan.', 1, 40, 17);

-- -----------------------------------------------------
-- 5. INSCRIPTIONS
-- -----------------------------------------------------
INSERT IGNORE INTO inscription (apprenant_id, cours_id, date_inscription, statut) VALUES
(4, 1, NOW(), 'EN_COURS'),
(4, 4, NOW(), 'EN_COURS'),
(5, 6, NOW(), 'EN_COURS');

-- -----------------------------------------------------
-- 6. QUIZ
-- -----------------------------------------------------
INSERT IGNORE INTO quiz (id, titre, description, score_minimum, module_id) VALUES
(1, 'Quiz Grammaire anglaise', 'Testez vos connaissances en grammaire anglaise', 3, 1),
(10, 'Quiz C Avancé - Mémoire', 'Validation des concepts mémoire', 4, 10),
(11, 'Quiz CCNA - Protocoles', 'Test sur les couches transport et réseau', 3, 12),
(12, 'Quiz ML - Modèles Supervisés', 'Évaluation sur les modèles de base', 4, 14);

-- -----------------------------------------------------
-- 7. QUESTIONS
-- -----------------------------------------------------
INSERT IGNORE INTO question (id, texte, choix1, choix2, choix3, choix4, bonne_reponse, points, quiz_id) VALUES
-- Quiz Anglais
(1, 'Comment dit-on "je suis" en anglais ?', 'I are', 'I am', 'I is', 'I be', 2, 1, 1),
-- Quiz C
(50, 'Quelle fonction initialise la mémoire à zéro lors de l allocation ?', 'malloc', 'realloc', 'calloc', 'free', 3, 2, 10),
(51, 'Que contient une variable de type pointeur ?', 'Une valeur entière', 'Une adresse mémoire', 'Un caractère', 'Un flottant', 2, 2, 10),
-- Quiz Réseaux
(55, 'Quel protocole garantit la livraison des paquets ?', 'UDP', 'IP', 'TCP', 'ICMP', 3, 1, 11),
-- Quiz ML
(60, 'Quel problème la régularisation cherche-t-elle à résoudre ?', 'Le biais élevé', 'L overfitting', 'La vitesse de calcul', 'Le manque de données', 2, 2, 12),
(61, 'Dans quel cas utilise-t-on une fonction d activation Sigmoïde ?', 'Classification binaire', 'Régression linéaire', 'Tri de données', 'Calcul de moyenne', 1, 2, 12);


-- Mise à jour des URLs d'images pour les cours existants
UPDATE cours SET image_url = 'https://anglopro.qc.ca/wp-content/uploads/2022/10/svg-anglais-site.svg' WHERE id = 1;
UPDATE cours SET image_url = 'https://i.pinimg.com/1200x/6b/ad/3d/6bad3dcdb68c3dbc33e4c95fce2578b0.jpg' WHERE id = 2;
UPDATE cours SET image_url = 'https://i.pinimg.com/736x/b9/27/52/b927526b9adba05083437bcd9ecabe5a.jpg' WHERE id = 3;
UPDATE cours SET image_url = 'https://cdn.eduonix.com/assets/images/header_img/2023113012273814660.jpg' WHERE id = 4;
UPDATE cours SET image_url = 'https://i.pinimg.com/736x/d6/6e/ea/d66eeaf6317890424833dc09cad5bbfb.jpg' WHERE id = 5;
UPDATE cours SET image_url = 'https://media.licdn.com/dms/image/v2/D5612AQEZghz63-om9Q/article-cover_image-shrink_720_1280/article-cover_image-shrink_720_1280/0/1692236731979?e=2147483647&v=beta&t=6mjsvAdqG6kfrjVkFNwHPYio2rA2H71AuIhH-1tHpII' WHERE id = 6;
UPDATE cours SET image_url = 'https://i.pinimg.com/1200x/2e/df/7f/2edf7facdfb1524b5508ef9284d9fc55.jpg' WHERE id = 7;