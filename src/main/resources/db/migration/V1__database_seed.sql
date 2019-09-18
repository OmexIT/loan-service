CREATE TABLE Seasons
(
    SeasonID   INTEGER PRIMARY KEY,
    SeasonName VARCHAR(200) NOT NULL,
    StartDate  DATE         NOT NULL,
    EndDate    DATE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE Customers
(
    CustomerID   INTEGER PRIMARY KEY,
    CustomerName VARCHAR(50)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE CustomerSummaries
(
    CustomerID  INTEGER,
    SeasonID    INTEGER,
    Credit      DECIMAL NOT NULL DEFAULT 0,
    TotalRepaid DECIMAL NOT NULL DEFAULT 0,
    FOREIGN KEY (CustomerID) REFERENCES Customers (CustomerID),
    FOREIGN KEY (SeasonID) REFERENCES Seasons (SeasonID)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;;

CREATE TABLE RepaymentUploads
(
    RepaymentUploadID INTEGER AUTO_INCREMENT PRIMARY KEY,
    CustomerID        INTEGER,
    SeasonID          INTEGER,
    Date              DATETIME NOT NULL DEFAULT NOW(),
    Amount            DECIMAL  NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customers (CustomerID),
    FOREIGN KEY (SeasonID) REFERENCES Seasons (SeasonID)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE Repayments
(
    RepaymentID INTEGER AUTO_INCREMENT PRIMARY KEY,
    CustomerID  INTEGER  NOT NULL,
    SeasonID    INTEGER  NOT NULL,
    Date        DATETIME NOT NULL DEFAULT NOW(),
    Amount      DECIMAL  NOT NULL,
    ParentID    INTEGER  NOT NULL,
    FOREIGN KEY (ParentID) REFERENCES RepaymentUploads (RepaymentUploadID),
    FOREIGN KEY (CustomerID) REFERENCES Customers (CustomerID),
    FOREIGN KEY (SeasonID) REFERENCES Seasons (SeasonID)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

