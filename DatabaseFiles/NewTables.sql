DROP TABLE IF EXISTS EO_PK_TABLE CASCADE;

DROP TABLE IF EXISTS Admin CASCADE;

DROP TABLE IF EXISTS Attendee CASCADE;

DROP TABLE IF EXISTS AttendeeFunction CASCADE;

DROP TABLE IF EXISTS AttendeeMealPreference CASCADE;

DROP TABLE IF EXISTS AttendeeType CASCADE;

DROP TABLE IF EXISTS Event CASCADE;

DROP TABLE IF EXISTS HotelInformation CASCADE;

DROP TABLE IF EXISTS InvitationLetterInformation CASCADE;

DROP TABLE IF EXISTS Invitee CASCADE;

DROP TABLE IF EXISTS TravelInformation CASCADE;

DROP TABLE IF EXISTS MyVenue CASCADE;

DROP TABLE IF EXISTS VenueAttendee CASCADE;

CREATE TABLE Admin (
	dateCreated DATETIME NOT NULL,
	dateLastLogin DATETIME,
	dateModified DATETIME NOT NULL,
	emailAddress TEXT NOT NULL,
	fullName TEXT NOT NULL,
	id INT NOT NULL,
	password TEXT NOT NULL,
	username TEXT NOT NULL
);

CREATE TABLE Attendee (
	addressCity TEXT NOT NULL,
	addressCountry TEXT NOT NULL,
	addressPostalCode TEXT NOT NULL,
	addressStreet1 TEXT NOT NULL,
	addressStreet2 TEXT,
	companyName TEXT,
	dateLastEdited DATETIME NOT NULL,
	dateLastVisit DATETIME NOT NULL,
	dateRegistered DATETIME NOT NULL,
	fax TEXT NOT NULL,
	id INT NOT NULL,
	idAttendee INT,
	idAttendeeFunction INT NOT NULL,
	idAttendeeMealPreference INT NOT NULL,
	idAttendeeType INT NOT NULL,
	idTravelInformation INT NOT NULL,
	jobTitle TEXT NOT NULL,
	nameFamily TEXT NOT NULL,
	nameGiven TEXT NOT NULL,
	phone TEXT NOT NULL,
	salutation TEXT,
	specialNeedsNotes TEXT NOT NULL,
	userEmailAddress TEXT NOT NULL,
	userPassword TEXT NOT NULL
);

CREATE TABLE AttendeeFunction (
	id INT NOT NULL,
	lable TEXT NOT NULL
);

CREATE TABLE AttendeeMealPreference (
	id INT NOT NULL,
	lable TEXT NOT NULL
);

CREATE TABLE AttendeeType (
	_pk INT NOT NULL,
	lable TEXT NOT NULL
);

CREATE TABLE Event (
	dateTimeEnd DATETIME NOT NULL,
	dateTimeStart DATETIME NOT NULL,
	description TEXT,
	id INT NOT NULL,
	idVenue INT NOT NULL,
	lable TEXT NOT NULL,
	meetingPlace TEXT NOT NULL
);

CREATE TABLE HotelInformation (
	checkIn DATETIME NOT NULL,
	checkOut DATETIME NOT NULL,
	hotelAddress TEXT NOT NULL,
	hotelName TEXT NOT NULL,
	id INT NOT NULL,
	numRooms INT NOT NULL,
	telephone TEXT NOT NULL
);

CREATE TABLE InvitationLetterInformation (
	birthDate DATE NOT NULL,
	birthPlace TEXT NOT NULL,
	dateArrival DATE NOT NULL,
	dateDeparture DATE NOT NULL,
	gender VARCHAR(50) NOT NULL,
	id INT NOT NULL,
	nameFamily TEXT NOT NULL,
	nameGiven TEXT NOT NULL,
	nameMiddle TEXT NOT NULL,
	nationality TEXT NOT NULL,
	passportDateExpire DATE NOT NULL,
	passportDateIssued DATE NOT NULL,
	passportNumber TEXT NOT NULL
);

CREATE TABLE Invitee (
	emailAddress TEXT NOT NULL,
	freeFormName TEXT NOT NULL,
	id INT NOT NULL,
	idAdmin INT NOT NULL,
	idVenue INT NOT NULL
);

CREATE TABLE TravelInformation (
	arrivalDate DATETIME NOT NULL,
	arrivalFlightNumber TEXT NOT NULL,
	arrivalFrom TEXT NOT NULL,
	departureDate DATETIME NOT NULL,
	departureFlightNumber TEXT NOT NULL,
	departureTo TEXT NOT NULL,
	id INT NOT NULL
);

CREATE TABLE MyVenue (
	dateCreated DATETIME NOT NULL,
	dateEnd DATETIME NOT NULL,
	dateModified DATETIME NOT NULL,
	dateStart DATE NOT NULL,
	description TEXT,
	id INT NOT NULL,
	idAdmin INT NOT NULL,
	inactive INT,
	lable TEXT NOT NULL,
	mapAddress TEXT NOT NULL,
	webpageURL TEXT
);

CREATE TABLE VenueAttendee (
	attendeeId INT NOT NULL,
	venueId INT NOT NULL
);

CREATE TABLE EO_PK_TABLE (
	NAME CHAR(40) PRIMARY KEY,
	PK INT
);

ALTER TABLE Admin ADD PRIMARY KEY (
	id
);

ALTER TABLE Attendee ADD PRIMARY KEY (
	id
);

ALTER TABLE AttendeeFunction ADD PRIMARY KEY (
	id
);

ALTER TABLE AttendeeMealPreference ADD PRIMARY KEY (
	id
);

ALTER TABLE AttendeeType ADD PRIMARY KEY (
	_pk
);

ALTER TABLE Event ADD PRIMARY KEY (
	id
);

ALTER TABLE HotelInformation ADD PRIMARY KEY (
	id
);

ALTER TABLE InvitationLetterInformation ADD PRIMARY KEY (
	id
);

ALTER TABLE Invitee ADD PRIMARY KEY (
	id
);

ALTER TABLE TravelInformation ADD PRIMARY KEY (
	id
);

ALTER TABLE MyVenue ADD PRIMARY KEY (
	id
);

ALTER TABLE VenueAttendee ADD PRIMARY KEY (
	attendeeId,
	venueId
);

