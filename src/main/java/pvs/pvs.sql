-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 03. Jun 2026 um 13:39
-- Server-Version: 10.4.32-MariaDB
-- PHP-Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `pvs`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `mitarbeiter`
--

CREATE TABLE `mitarbeiter` (
  `id` int(11) NOT NULL,
  `personalnummer` varchar(10) DEFAULT NULL,
  `nachname` varchar(100) NOT NULL,
  `vorname` varchar(100) DEFAULT NULL,
  `strasse` varchar(100) DEFAULT NULL,
  `hausnummer` varchar(10) DEFAULT NULL,
  `ort` int(11) DEFAULT NULL,
  `ressort` int(11) DEFAULT NULL,
  `geburtsdatum` date DEFAULT NULL,
  `vertragstyp` int(11) DEFAULT 1,
  `titel` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `mitarbeiter`
--

INSERT INTO `mitarbeiter` (`id`, `personalnummer`, `nachname`, `vorname`, `strasse`, `hausnummer`, `ort`, `ressort`, `geburtsdatum`, `vertragstyp`, `titel`) VALUES
(1, '2701007', 'Groß', 'Henriette', 'Engelbertstraße', '23', 2, 1, '1975-02-25', 1, NULL),
(2, '2802123', 'Holstein-Groß', 'Manfred', 'Engelbertstr.', '23', 2, 1, '1966-07-02', 1, NULL),
(3, '2903258', 'Ganz', 'Anette', 'Engelbertstr.', '25', 2, 1, '1999-11-13', 1, NULL),
(4, '3004951', 'Brown', 'Sharon', 'Engelbertstr.', '26', 2, 1, '1998-10-14', 1, NULL),
(5, '3105351', 'Neuneier', 'Felix', 'Professor-Kneib-Str.', '349', 3, 1, '1959-04-22', 1, NULL),
(6, NULL, 'Neuneier', 'Sabiene', 'Professor-Kneib-Str.', '349', 3, 1, '1997-08-11', 2, NULL),
(8, '3206369', 'Neumann', 'Lisa', 'Gartenstraße', '20', 4, NULL, '2001-06-08', 2, NULL),
(9, '3206555', 'Mallmann', 'Peter', 'Hinter den Höfen', '11', 5, 2, '1980-02-29', 1, NULL),
(10, '3307951', 'Wieselbaum', 'Joachim', 'Lahnstr.', '24', 6, 2, '1982-05-05', 1, NULL),
(11, '3408069', 'Bier', 'Justin', 'Mainzer Str.', '34', 7, 2, '2002-09-13', 1, NULL),
(12, '3509159', 'Walder', 'Jessica', 'Schneebergstraße', '36', 8, 3, '1986-10-12', 1, NULL),
(13, '3510582', 'Hegenbergh', 'Hendrik', 'Hollersborn', '35', 9, 3, '1979-01-01', 1, NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `mitarbeiter_projekte`
--

CREATE TABLE `mitarbeiter_projekte` (
  `id_mitarbeiter` int(11) NOT NULL,
  `id_projekt` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `mitarbeiter_projekte`
--

INSERT INTO `mitarbeiter_projekte` (`id_mitarbeiter`, `id_projekt`) VALUES
(1, 1),
(1, 3),
(2, 5),
(3, 1),
(3, 3),
(4, 1),
(4, 3),
(5, 1),
(5, 3),
(8, 1),
(8, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `nutzer`
--

CREATE TABLE `nutzer` (
  `user_id` int(11) NOT NULL,
  `passwort` varchar(100) DEFAULT NULL,
  `rollen_id` int(11) DEFAULT NULL,
  `email_adresse` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `orte`
--

CREATE TABLE `orte` (
  `id` int(11) NOT NULL,
  `plz` varchar(15) DEFAULT NULL,
  `ortsname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `orte`
--

INSERT INTO `orte` (`id`, `plz`, `ortsname`) VALUES
(1, '56271', 'Kleinmaischeid'),
(2, '45739', 'Oer-Erkenschwick'),
(3, '55270', 'Zornheim'),
(4, '56203', 'Höhr-Grenzhausen'),
(5, '22946', 'Trittau'),
(6, '56235', 'Ransbach-Baumbach'),
(7, '56154', 'Boppard'),
(8, '56204', 'Hillscheid'),
(9, '56203', 'Grenzau');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `projekte`
--

CREATE TABLE `projekte` (
  `id` int(11) NOT NULL,
  `bezeichnung` varchar(50) DEFAULT NULL,
  `beginn` date DEFAULT NULL,
  `abschluss` date DEFAULT NULL,
  `planabschluss` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `projekte`
--

INSERT INTO `projekte` (`id`, `bezeichnung`, `beginn`, `abschluss`, `planabschluss`) VALUES
(1, 'Aus Grau wird Bunt', '2024-08-01', '2025-05-30', NULL),
(2, 'Money Money Money', '2020-07-06', '2022-11-28', NULL),
(3, 'Grün, grüner, unsere Stadt', '2022-07-01', NULL, NULL),
(5, 'Schwimmende Tribüne', '2021-08-11', NULL, NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `projektleitung`
--

CREATE TABLE `projektleitung` (
  `projektleitung_id` int(11) NOT NULL,
  `projekt_id` int(11) NOT NULL,
  `mitarbeiter_id` int(11) NOT NULL,
  `von` date NOT NULL,
  `bis` date NOT NULL
) ;

--
-- Trigger `projektleitung`
--
DELIMITER $$
CREATE TRIGGER `trg_projektleitung_insert` BEFORE INSERT ON `projektleitung` FOR EACH ROW BEGIN
    DECLARE v_beginn DATE;
    DECLARE v_ende DATE;

    SELECT projektbeginn, geplanter_abschluss
    INTO v_beginn, v_ende
    FROM projekte
    WHERE projekt_id = NEW.projekt_id;

    -- Projektleitung muss innerhalb der Projektdauer liegen
    IF NEW.von < v_beginn OR NEW.bis > v_ende THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
        'Projektleitungszeitraum liegt außerhalb der Projektdauer';
    END IF;

    -- Keine Überschneidungen zulassen
    IF EXISTS (
        SELECT 1
        FROM projektleitung pl
        WHERE pl.projekt_id = NEW.projekt_id
          AND NEW.von <= pl.bis
          AND NEW.bis >= pl.von
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
        'Für dieses Projekt existiert bereits ein Projektleiter im angegebenen Zeitraum';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_projektleitung_update` BEFORE UPDATE ON `projektleitung` FOR EACH ROW BEGIN
    IF EXISTS (
        SELECT 1
        FROM projektleitung pl
        WHERE pl.projekt_id = NEW.projekt_id
          AND pl.projektleitung_id <> NEW.projektleitung_id
          AND NEW.von <= pl.bis
          AND NEW.bis >= pl.von
    ) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT =
        'Dieses Projekt hat in diesem Zeitraum bereits einen Projektleiter';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `ressorts`
--

CREATE TABLE `ressorts` (
  `id` int(11) NOT NULL,
  `bezeichnung` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `ressorts`
--

INSERT INTO `ressorts` (`id`, `bezeichnung`) VALUES
(2, 'Bauamt'),
(3, 'Ordnungsamt'),
(1, 'Stadtgärtnerei');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tickets`
--

CREATE TABLE `tickets` (
  `id` int(11) NOT NULL,
  `grund` text DEFAULT NULL,
  `zeitpunkt` datetime DEFAULT NULL,
  `aussteller1` int(11) DEFAULT NULL,
  `aussteller2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `tickets`
--

INSERT INTO `tickets` (`id`, `grund`, `zeitpunkt`, `aussteller1`, `aussteller2`) VALUES
(1, 'Bewegt sich unerlaubt ohne Personalnummer auf dem Betriebsgelände', '2021-03-24 11:23:00', NULL, NULL);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `titel`
--

CREATE TABLE `titel` (
  `id` int(11) NOT NULL,
  `bezeichnung` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `titel`
--

INSERT INTO `titel` (`id`, `bezeichnung`) VALUES
(1, 'Projektleiter'),
(2, 'VP'),
(3, 'Director');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `vertragstypen`
--

CREATE TABLE `vertragstypen` (
  `id` int(11) NOT NULL,
  `bezeichnung` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `vertragstypen`
--

INSERT INTO `vertragstypen` (`id`, `bezeichnung`) VALUES
(1, 'Mitarbeiter'),
(2, 'Auszubildender'),
(3, 'Praktikant'),
(4, 'Doktorand');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `verursacher`
--

CREATE TABLE `verursacher` (
  `id_mitarbeiter` int(11) DEFAULT NULL,
  `id_vergehen` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `verursacher`
--

INSERT INTO `verursacher` (`id_mitarbeiter`, `id_vergehen`) VALUES
(12, 1),
(13, 1);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `mitarbeiter`
--
ALTER TABLE `mitarbeiter`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `personalnummer` (`personalnummer`),
  ADD KEY `const_mitarbeiter_ort` (`ort`),
  ADD KEY `const_mitarbeiter_ressort` (`ressort`),
  ADD KEY `fk_rolle` (`vertragstyp`),
  ADD KEY `titel` (`titel`);

--
-- Indizes für die Tabelle `mitarbeiter_projekte`
--
ALTER TABLE `mitarbeiter_projekte`
  ADD PRIMARY KEY (`id_mitarbeiter`,`id_projekt`),
  ADD KEY `const_projekt_maprojekte` (`id_projekt`);

--
-- Indizes für die Tabelle `nutzer`
--
ALTER TABLE `nutzer`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `rollen_id` (`rollen_id`);

--
-- Indizes für die Tabelle `orte`
--
ALTER TABLE `orte`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `projekte`
--
ALTER TABLE `projekte`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `bezeichnung` (`bezeichnung`);

--
-- Indizes für die Tabelle `projektleitung`
--
ALTER TABLE `projektleitung`
  ADD PRIMARY KEY (`projektleitung_id`),
  ADD KEY `projekt_id` (`projekt_id`),
  ADD KEY `mitarbeiter_id` (`mitarbeiter_id`);

--
-- Indizes für die Tabelle `ressorts`
--
ALTER TABLE `ressorts`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `bezeichnung` (`bezeichnung`);

--
-- Indizes für die Tabelle `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `aussteller1` (`aussteller1`),
  ADD KEY `aussteller2` (`aussteller2`);

--
-- Indizes für die Tabelle `titel`
--
ALTER TABLE `titel`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `vertragstypen`
--
ALTER TABLE `vertragstypen`
  ADD PRIMARY KEY (`id`);

--
-- Indizes für die Tabelle `verursacher`
--
ALTER TABLE `verursacher`
  ADD KEY `mitarbeiter_verraeter` (`id_mitarbeiter`),
  ADD KEY `vergehen_verraeter` (`id_vergehen`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `mitarbeiter`
--
ALTER TABLE `mitarbeiter`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT für Tabelle `nutzer`
--
ALTER TABLE `nutzer`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `orte`
--
ALTER TABLE `orte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT für Tabelle `projekte`
--
ALTER TABLE `projekte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `projektleitung`
--
ALTER TABLE `projektleitung`
  MODIFY `projektleitung_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT für Tabelle `ressorts`
--
ALTER TABLE `ressorts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT für Tabelle `titel`
--
ALTER TABLE `titel`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `vertragstypen`
--
ALTER TABLE `vertragstypen`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `mitarbeiter`
--
ALTER TABLE `mitarbeiter`
  ADD CONSTRAINT `const_mitarbeiter_ort` FOREIGN KEY (`ort`) REFERENCES `orte` (`id`),
  ADD CONSTRAINT `const_mitarbeiter_ressort` FOREIGN KEY (`ressort`) REFERENCES `ressorts` (`id`),
  ADD CONSTRAINT `fk_rolle` FOREIGN KEY (`vertragstyp`) REFERENCES `vertragstypen` (`id`),
  ADD CONSTRAINT `mitarbeiter_ibfk_1` FOREIGN KEY (`titel`) REFERENCES `titel` (`id`),
  ADD CONSTRAINT `mitarbeiter_ibfk_2` FOREIGN KEY (`titel`) REFERENCES `titel` (`id`),
  ADD CONSTRAINT `mitarbeiter_ibfk_3` FOREIGN KEY (`titel`) REFERENCES `titel` (`id`);

--
-- Constraints der Tabelle `mitarbeiter_projekte`
--
ALTER TABLE `mitarbeiter_projekte`
  ADD CONSTRAINT `const_mitarbeiter_maprojekte` FOREIGN KEY (`id_mitarbeiter`) REFERENCES `mitarbeiter` (`id`),
  ADD CONSTRAINT `const_projekt_maprojekte` FOREIGN KEY (`id_projekt`) REFERENCES `projekte` (`id`);

--
-- Constraints der Tabelle `nutzer`
--
ALTER TABLE `nutzer`
  ADD CONSTRAINT `nutzer_ibfk_1` FOREIGN KEY (`rollen_id`) REFERENCES `titel` (`id`);

--
-- Constraints der Tabelle `projektleitung`
--
ALTER TABLE `projektleitung`
  ADD CONSTRAINT `projektleitung_ibfk_1` FOREIGN KEY (`projekt_id`) REFERENCES `projekte` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `projektleitung_ibfk_2` FOREIGN KEY (`mitarbeiter_id`) REFERENCES `mitarbeiter` (`id`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `verursacher`
--
ALTER TABLE `verursacher`
  ADD CONSTRAINT `mitarbeiter_verraeter` FOREIGN KEY (`id_mitarbeiter`) REFERENCES `mitarbeiter` (`id`),
  ADD CONSTRAINT `vergehen_verraeter` FOREIGN KEY (`id_vergehen`) REFERENCES `tickets` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
