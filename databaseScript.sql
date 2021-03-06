CREATE FUNCTION [dbo].[dohvatiMagacin]
(
  @idS ID
)
RETURNS ID
AS
BEGIN
	return 
	(
	Select M.IdMag
	from Magacin M, Sprat S, Objekat O
	where S.IdSpr = @idS and S.IdObj = O.IdObj and O.IdGr = M.IdGr
	)

END
GO

CREATE FUNCTION [dbo].[proveraDatumaRada]
(
   @datum date,
   @idp ID
)
RETURNS INT
AS
BEGIN
	DECLARE @datumK date,
			@datumP date

	select @datumK = DatumKraja, @datumP = DatumPocetka 
	from Posao 
	where IdPos = @idp and Status = 'U'
	
	if(@@ROWCOUNT = 0 or @datum > @datumK or @datum < @datumP)
		return -1

	return 1
END
GO

CREATE TABLE [dbo].[Gradiliste](
	[IdGr] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Naziv] [varchar](50) NOT NULL,
	[DatumOsnivanja] [date] NOT NULL,
	[BrObjekata] [int] NOT NULL,
 CONSTRAINT [XPKGradiliste] PRIMARY KEY CLUSTERED 
(
	[IdGr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UniqueGradiliste] UNIQUE NONCLUSTERED 
(
	[DatumOsnivanja] ASC,
	[Naziv] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Je_Zaposlen]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Je_Zaposlen](
	[IdZap] [dbo].[ID] NOT NULL,
	[JeSef] [bit] NOT NULL,
	[IdMag] [dbo].[ID] NOT NULL,
 CONSTRAINT [XPKJe_Zaposlen] PRIMARY KEY CLUSTERED 
(
	[IdZap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Magacin]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Magacin](
	[IdMag] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Plata] [decimal](10, 3) NOT NULL,
	[IdGr] [dbo].[ID] NOT NULL,
 CONSTRAINT [XPKMagacin] PRIMARY KEY CLUSTERED 
(
	[IdMag] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UniqueMagGrad] UNIQUE NONCLUSTERED 
(
	[IdGr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Norma]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Norma](
	[IdNor] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Naziv] [varchar](50) NOT NULL,
	[CenaIzrade] [decimal](10, 3) NOT NULL,
	[PlataRadnika] [decimal](10, 3) NOT NULL,
 CONSTRAINT [XPKNorma] PRIMARY KEY CLUSTERED 
(
	[IdNor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Objekat]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Objekat](
	[IdObj] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Naziv] [varchar](50) NOT NULL,
	[BrojSpratova] [int] NOT NULL,
	[IdGr] [dbo].[ID] NOT NULL,
 CONSTRAINT [XPKObjekat] PRIMARY KEY CLUSTERED 
(
	[IdObj] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Posao]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Posao](
	[IdPos] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Status] [char](1) NULL,
	[DatumPocetka] [date] NOT NULL,
	[DatumKraja] [date] NULL,
	[IdNor] [dbo].[ID] NOT NULL,
	[IdSpr] [dbo].[ID] NOT NULL,
 CONSTRAINT [XPKPosao] PRIMARY KEY CLUSTERED 
(
	[IdPos] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Potrebna_Roba]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Potrebna_Roba](
	[Kol_Jed] [decimal](10, 3) NOT NULL,
	[IdNor] [dbo].[ID] NOT NULL,
	[IdR] [dbo].[ID] NOT NULL,
	[IdPotrR] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[JedMere] [char](1) NOT NULL,
 CONSTRAINT [XPKPotrebna_Roba] PRIMARY KEY CLUSTERED 
(
	[IdPotrR] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UniquePotraznja] UNIQUE NONCLUSTERED 
(
	[IdNor] ASC,
	[IdR] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Radi_Posao]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Radi_Posao](
	[DatumPocetka] [date] NOT NULL,
	[DatumKraja] [date] NULL,
	[Ocena] [int] NULL,
	[IdZap] [dbo].[ID] NOT NULL,
	[IdPos] [dbo].[ID] NOT NULL,
	[IdRadiPos] [dbo].[ID] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [XPKRadi_Posao] PRIMARY KEY CLUSTERED 
(
	[IdRadiPos] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UniqueRadio] UNIQUE NONCLUSTERED 
(
	[IdPos] ASC,
	[IdZap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Roba]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Roba](
	[IdR] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Naziv] [varchar](50) NOT NULL,
	[Kod] [varchar](50) NOT NULL,
	[IdTip] [dbo].[ID] NOT NULL,
 CONSTRAINT [XPKRoba] PRIMARY KEY CLUSTERED 
(
	[IdR] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Kod] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Sprat]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sprat](
	[IdSpr] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[RedniBroj] [int] NOT NULL,
	[IdObj] [dbo].[ID] NOT NULL,
 CONSTRAINT [XPKSprat] PRIMARY KEY CLUSTERED 
(
	[IdSpr] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Tip]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Tip](
	[IdTip] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Naziv] [varchar](50) NOT NULL,
 CONSTRAINT [XPKTip] PRIMARY KEY CLUSTERED 
(
	[IdTip] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UniqueTipNaziv] UNIQUE NONCLUSTERED 
(
	[Naziv] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[U_Magacinu]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[U_Magacinu](
	[IdR] [dbo].[ID] NOT NULL,
	[IdMag] [dbo].[ID] NOT NULL,
	[Kol_Jed] [decimal](10, 3) NOT NULL,
	[IdUmag] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[JedMere] [char](1) NOT NULL,
 CONSTRAINT [XPKU_Magacinu] PRIMARY KEY CLUSTERED 
(
	[IdUmag] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [UniqueSmesten] UNIQUE NONCLUSTERED 
(
	[IdMag] ASC,
	[IdR] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Zaduzenje_Opreme]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Zaduzenje_Opreme](
	[IdZap] [dbo].[ID] NOT NULL,
	[IdR] [dbo].[ID] NOT NULL,
	[IdMag] [dbo].[ID] NOT NULL,
	[DatumZaduzenja] [date] NOT NULL,
	[DatumRazduzenja] [date] NULL,
	[Napomena] [varchar](50) NULL,
	[IdZaduz] [dbo].[ID] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [XPKZaduzenje_Opreme] PRIMARY KEY CLUSTERED 
(
	[IdZaduz] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Zaposleni]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Zaposleni](
	[IdZap] [dbo].[ID] IDENTITY(1,1) NOT NULL,
	[Ime] [varchar](50) NOT NULL,
	[Prezime] [varchar](50) NOT NULL,
	[JMBG] [char](13) NOT NULL,
	[Pol] [char](1) NOT NULL,
	[ZiroRacun] [varchar](50) NOT NULL,
	[Email] [varchar](50) NOT NULL,
	[BrTelefona] [varchar](50) NOT NULL,
	[Prosek] [decimal](10, 3) NOT NULL,
	[ZaduzenoOpreme] [int] NOT NULL,
	[Zarada] [decimal](10, 3) NOT NULL,
 CONSTRAINT [XPKZaposleni] PRIMARY KEY CLUSTERED 
(
	[IdZap] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Gradiliste] ADD  CONSTRAINT [PocetnaVrednostObj]  DEFAULT ((0)) FOR [BrObjekata]
GO
ALTER TABLE [dbo].[Objekat] ADD  CONSTRAINT [PocetnaVrednostSpatovi]  DEFAULT ((0)) FOR [BrojSpratova]
GO
ALTER TABLE [dbo].[Posao] ADD  CONSTRAINT [PocetanStatus]  DEFAULT ('U') FOR [Status]
GO
ALTER TABLE [dbo].[Potrebna_Roba] ADD  CONSTRAINT [DF_Potrebna_Roba_JedMere]  DEFAULT ('J') FOR [JedMere]
GO
ALTER TABLE [dbo].[Zaposleni] ADD  CONSTRAINT [ProsekDefault]  DEFAULT ((10)) FOR [Prosek]
GO
ALTER TABLE [dbo].[Zaposleni] ADD  CONSTRAINT [PocetnaVrednostZaduzenja]  DEFAULT ((0)) FOR [ZaduzenoOpreme]
GO
ALTER TABLE [dbo].[Zaposleni] ADD  CONSTRAINT [PocetnaVrednostZarade]  DEFAULT ((0)) FOR [Zarada]
GO
ALTER TABLE [dbo].[Je_Zaposlen]  WITH CHECK ADD  CONSTRAINT [R_6] FOREIGN KEY([IdZap])
REFERENCES [dbo].[Zaposleni] ([IdZap])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Je_Zaposlen] CHECK CONSTRAINT [R_6]
GO
ALTER TABLE [dbo].[Je_Zaposlen]  WITH CHECK ADD  CONSTRAINT [R_7] FOREIGN KEY([IdMag])
REFERENCES [dbo].[Magacin] ([IdMag])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Je_Zaposlen] CHECK CONSTRAINT [R_7]
GO
ALTER TABLE [dbo].[Magacin]  WITH CHECK ADD  CONSTRAINT [R_2] FOREIGN KEY([IdGr])
REFERENCES [dbo].[Gradiliste] ([IdGr])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Magacin] CHECK CONSTRAINT [R_2]
GO
ALTER TABLE [dbo].[Objekat]  WITH CHECK ADD  CONSTRAINT [R_12] FOREIGN KEY([IdGr])
REFERENCES [dbo].[Gradiliste] ([IdGr])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Objekat] CHECK CONSTRAINT [R_12]
GO
ALTER TABLE [dbo].[Posao]  WITH CHECK ADD  CONSTRAINT [R_19] FOREIGN KEY([IdNor])
REFERENCES [dbo].[Norma] ([IdNor])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Posao] CHECK CONSTRAINT [R_19]
GO
ALTER TABLE [dbo].[Posao]  WITH CHECK ADD  CONSTRAINT [R_21] FOREIGN KEY([IdSpr])
REFERENCES [dbo].[Sprat] ([IdSpr])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Posao] CHECK CONSTRAINT [R_21]
GO
ALTER TABLE [dbo].[Potrebna_Roba]  WITH CHECK ADD  CONSTRAINT [R_17] FOREIGN KEY([IdNor])
REFERENCES [dbo].[Norma] ([IdNor])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Potrebna_Roba] CHECK CONSTRAINT [R_17]
GO
ALTER TABLE [dbo].[Potrebna_Roba]  WITH CHECK ADD  CONSTRAINT [R_18] FOREIGN KEY([IdR])
REFERENCES [dbo].[Roba] ([IdR])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Potrebna_Roba] CHECK CONSTRAINT [R_18]
GO
ALTER TABLE [dbo].[Radi_Posao]  WITH CHECK ADD  CONSTRAINT [R_22] FOREIGN KEY([IdZap])
REFERENCES [dbo].[Zaposleni] ([IdZap])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Radi_Posao] CHECK CONSTRAINT [R_22]
GO
ALTER TABLE [dbo].[Radi_Posao]  WITH CHECK ADD  CONSTRAINT [R_23] FOREIGN KEY([IdPos])
REFERENCES [dbo].[Posao] ([IdPos])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Radi_Posao] CHECK CONSTRAINT [R_23]
GO
ALTER TABLE [dbo].[Roba]  WITH CHECK ADD  CONSTRAINT [R_1] FOREIGN KEY([IdTip])
REFERENCES [dbo].[Tip] ([IdTip])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Roba] CHECK CONSTRAINT [R_1]
GO
ALTER TABLE [dbo].[Sprat]  WITH CHECK ADD  CONSTRAINT [R_13] FOREIGN KEY([IdObj])
REFERENCES [dbo].[Objekat] ([IdObj])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Sprat] CHECK CONSTRAINT [R_13]
GO
ALTER TABLE [dbo].[U_Magacinu]  WITH CHECK ADD  CONSTRAINT [R_3] FOREIGN KEY([IdR])
REFERENCES [dbo].[Roba] ([IdR])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[U_Magacinu] CHECK CONSTRAINT [R_3]
GO
ALTER TABLE [dbo].[U_Magacinu]  WITH CHECK ADD  CONSTRAINT [R_4] FOREIGN KEY([IdMag])
REFERENCES [dbo].[Magacin] ([IdMag])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[U_Magacinu] CHECK CONSTRAINT [R_4]
GO
ALTER TABLE [dbo].[Zaduzenje_Opreme]  WITH CHECK ADD  CONSTRAINT [R_10] FOREIGN KEY([IdR])
REFERENCES [dbo].[Roba] ([IdR])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Zaduzenje_Opreme] CHECK CONSTRAINT [R_10]
GO
ALTER TABLE [dbo].[Zaduzenje_Opreme]  WITH CHECK ADD  CONSTRAINT [R_11] FOREIGN KEY([IdMag])
REFERENCES [dbo].[Magacin] ([IdMag])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Zaduzenje_Opreme] CHECK CONSTRAINT [R_11]
GO
ALTER TABLE [dbo].[Zaduzenje_Opreme]  WITH CHECK ADD  CONSTRAINT [R_8] FOREIGN KEY([IdZap])
REFERENCES [dbo].[Zaposleni] ([IdZap])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Zaduzenje_Opreme] CHECK CONSTRAINT [R_8]
GO
ALTER TABLE [dbo].[Norma]  WITH CHECK ADD  CONSTRAINT [IsplataConstr] CHECK  (([PlataRadnika]>(0)))
GO
ALTER TABLE [dbo].[Norma] CHECK CONSTRAINT [IsplataConstr]
GO
ALTER TABLE [dbo].[Norma]  WITH CHECK ADD  CONSTRAINT [JedCenaConstr] CHECK  (([CenaIzrade]>(0)))
GO
ALTER TABLE [dbo].[Norma] CHECK CONSTRAINT [JedCenaConstr]
GO
ALTER TABLE [dbo].[Posao]  WITH CHECK ADD  CONSTRAINT [StatusConstr] CHECK  (([Status]='U' OR [Status]='Z'))
GO
ALTER TABLE [dbo].[Posao] CHECK CONSTRAINT [StatusConstr]
GO
ALTER TABLE [dbo].[Potrebna_Roba]  WITH CHECK ADD  CONSTRAINT [JedinicaMere] CHECK  (([JedMere]='J' OR [JedMere]='K'))
GO
ALTER TABLE [dbo].[Potrebna_Roba] CHECK CONSTRAINT [JedinicaMere]
GO
ALTER TABLE [dbo].[Potrebna_Roba]  WITH CHECK ADD  CONSTRAINT [PozitivnaKolConstr] CHECK  (([Kol_Jed]>(0)))
GO
ALTER TABLE [dbo].[Potrebna_Roba] CHECK CONSTRAINT [PozitivnaKolConstr]
GO
ALTER TABLE [dbo].[Radi_Posao]  WITH CHECK ADD  CONSTRAINT [OcenaConstr] CHECK  (([Ocena]>=(1) AND [Ocena]<=(10)))
GO
ALTER TABLE [dbo].[Radi_Posao] CHECK CONSTRAINT [OcenaConstr]
GO
ALTER TABLE [dbo].[Sprat]  WITH CHECK ADD  CONSTRAINT [PozitivanSprat] CHECK  (([RedniBroj]>=(0)))
GO
ALTER TABLE [dbo].[Sprat] CHECK CONSTRAINT [PozitivanSprat]
GO
ALTER TABLE [dbo].[U_Magacinu]  WITH CHECK ADD  CONSTRAINT [JedinicaMereConstr] CHECK  (([JedMere]='J' OR [JedMere]='K'))
GO
ALTER TABLE [dbo].[U_Magacinu] CHECK CONSTRAINT [JedinicaMereConstr]
GO
ALTER TABLE [dbo].[U_Magacinu]  WITH CHECK ADD  CONSTRAINT [Pozitivno] CHECK  (([Kol_Jed]>(0)))
GO
ALTER TABLE [dbo].[U_Magacinu] CHECK CONSTRAINT [Pozitivno]
GO
ALTER TABLE [dbo].[Zaposleni]  WITH CHECK ADD  CONSTRAINT [JMBGConstr] CHECK  ((isnumeric([JMBG])=(1) AND len([JMBG])=(13)))
GO
ALTER TABLE [dbo].[Zaposleni] CHECK CONSTRAINT [JMBGConstr]
GO
ALTER TABLE [dbo].[Zaposleni]  WITH CHECK ADD  CONSTRAINT [PolConstr] CHECK  (([POL]='Z' OR [POL]='M'))
GO
ALTER TABLE [dbo].[Zaposleni] CHECK CONSTRAINT [PolConstr]
GO
/****** Object:  StoredProcedure [dbo].[SPIsplatiMagacinPlatu]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPIsplatiMagacinPlatu]
@idMag ID
AS
BEGIN
	DECLARE @kursor cursor,
			@idZap ID,
			@plata decimal(10,3)

			set @kursor = cursor for
			select IdZap
			from Je_Zaposlen
			where IdMag = @idMag

			select @plata = Plata
			from Magacin
			where IdMag = @idMag

			open @kursor
			
			fetch next from @kursor into @idZap

			while @@FETCH_STATUS = 0 begin
			update Zaposleni
			set Zarada = Zarada + @plata
			where IdZap = @idZap

			fetch next from @kursor into @idZap
			end
END
GO
/****** Object:  StoredProcedure [dbo].[SPIsplatiPosao]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPIsplatiPosao]
@IdP ID
AS
BEGIN
	DECLARE @kursor cursor,
			@idz ID,
			@datumP date,
			@datumKr date,
			@trajanjePosla int,
			@norma DECIMAL(10,3),
			@datumKP date,
			@datumPP date

	set @kursor = cursor for
	select IdZap, DatumKraja, DatumPocetka
	from Radi_Posao
	where IdPos = @IdP

	select @norma = N.PlataRadnika
	from Norma N, Posao P
	where P.IdPos = @IdP and P.IdNor = N.IdNor

	select @datumKP = DatumKraja, @datumPP = DatumPocetka
	from Posao
	where IdPos = @IdP

	set @trajanjePosla = DATEDIFF(day,@datumPP,@datumKP) + 1

	open @kursor

	fetch next from @kursor into
	@idz, @datumKr, @datumP

	while @@FETCH_STATUS = 0 begin
		update Zaposleni
		set Zarada = Zarada + Prosek*@norma*(DATEDIFF(day,@datumP, @datumKr) + 1)/@trajanjePosla
		where IdZap = @idz
		fetch next from @kursor into
		@idz, @datumKr, @datumP
	end

	close @kursor
	deallocate @kursor
END
GO
/****** Object:  StoredProcedure [dbo].[SPRacunajProsek]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPRacunajProsek]
@idZ ID
AS
BEGIN
	declare @novo decimal(10,3)

	select @novo = avg(cast (Ocena as Decimal(10,3)))
	from Radi_Posao
	where IdZap = @idZ and Ocena is not null
	
	if @novo is null
	set @novo = 10

	Update Zaposleni
	set Prosek = @novo
	where IdZap = @idZ

END
GO
/****** Object:  StoredProcedure [dbo].[SPSmanjiRobu]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPSmanjiRobu]
	@IdMag ID,
	@IdR ID,
	@broj decimal(10,3)
AS
BEGIN
Declare @brojnoStanje decimal(10,3)
	
	Select @brojnoStanje = Kol_Jed 
	From U_Magacinu
	where IdR = @IdR and IdMag = @IdMag
	
	if(@@ROWCOUNT = 0)
		Return -1

	if(@brojnoStanje <= @broj) begin
	delete from U_Magacinu
	where IdR = @IdR and IdMag = @IdMag
	return @brojnoStanje
	end

	update U_Magacinu
	set Kol_Jed = @brojnoStanje - @broj
	where IdR = @IdR and IdMag = @IdMag 
	return @broj

END
GO
/****** Object:  StoredProcedure [dbo].[SPUvecajRobu]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SPUvecajRobu]
	@IdMag ID,
	@IdR ID,
	@broj int,
	@mera char
AS
BEGIN
Declare @brojnoStanje int,
		@naziv varchar(50),
		@tip char
	
	Select @brojnoStanje = Kol_Jed 
	From U_Magacinu
	where IdR = @IdR and IdMag = @IdMag
	
	if(@@ROWCOUNT = 0) begin
		insert into U_Magacinu(IdR, IdMag, Kol_Jed, JedMere)
		VALUES (@IdR, @IdMag, @broj, @mera)
	end
	else begin
	update U_Magacinu
	set Kol_Jed = Kol_Jed + @broj
	where IdR = @IdR and IdMag = @IdMag 
	end
END
GO
/****** Object:  StoredProcedure [dbo].[validnaJedinicaMere]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[validnaJedinicaMere]
    @idMag ID,
	@idR ID,
	@jed char

AS
BEGIN
    SELECT * FROM U_Magacinu
	WHERE @idMag = IdMag and IdR = @idR and JedMere = @jed

	if(@@ROWCOUNT = 0)
		RETURN -1
	
	RETURN 1

END
GO
/****** Object:  Trigger [dbo].[InsertJeZaposlen]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[InsertJeZaposlen] --nije testiran
    ON [dbo].[Je_Zaposlen]
    FOR INSERT
    AS
    BEGIN
		Declare @IdZap ID,
				@IdMag ID,
				@broj int
				
		Select @IdZap = IdZap, @IdMag = IdMag
		from inserted


		select @broj = COUNT(*)
		from Radi_Posao
		where IdZap = @IdZap and DatumKraja is null

		if(@broj > 0) begin
			print 'Zaposleni vec radi na nekom poslu'
			rollback transaction
			end
    END
GO
/****** Object:  Trigger [dbo].[InsertMagacin]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[InsertMagacin]
    ON [dbo].[Magacin]
    FOR INSERT
    AS
    BEGIN
    Declare @idGr ID,
			@broj int

	Select @idGr = IdGr
	from inserted

	select @broj = count(*)
	from Magacin
	where IdGr = @idGr

	if(@broj > 1)
	begin
	print 'Gradiliste moze imati 1 magacin'
	rollback transaction
	end

    END

GO
/****** Object:  Trigger [dbo].[InsertObjekat]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[InsertObjekat]
    ON [dbo].[Objekat]
    FOR INSERT
    AS
    BEGIN
	Declare @idGr ID

	Select @idGr = IdGr
	from inserted
		
		Update Gradiliste
		set BrObjekata = 1+ BrObjekata
		where IdGr = @idGr

    END

GO
/****** Object:  Trigger [dbo].[ObrisiObjekat]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[ObrisiObjekat]
    ON [dbo].[Objekat]
    FOR DELETE
    AS
    BEGIN
	Declare @idGr ID

	Select @idGr = IdGr
	from deleted
		
		Update Gradiliste
		set BrObjekata = BrObjekata - 1
		where IdGr = @idGr
    END
GO
/****** Object:  Trigger [dbo].[DeletePosao]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[DeletePosao]
    ON [dbo].[Posao]
    FOR DELETE
    AS
    BEGIN
	Declare @idS ID,
			@idN ID,
			@idMag ID,
			@mera char

		select @idS = IdSpr, @idN = IdNor
		from deleted
		
		set @idMag = dbo.dohvatiMagacin(@idS)

		Declare @kursor cursor,
				@kolicina int,
				@status int,
				@idR ID
			
		set @kursor = cursor for
		select IdR, Kol_Jed, JedMere 
		from Potrebna_Roba
		where IdNor = @idN
			
		open @kursor

		fetch next from @kursor into
		@idR, @kolicina, @mera

		while @@FETCH_STATUS = 0 begin
				execute @status = SPUvecajRobu @IdMag, @IdR, @kolicina, @mera
			
				fetch next from @kursor into
				@idR, @kolicina, @mera
		end

			close @kursor
			deallocate @kursor
    END
GO
/****** Object:  Trigger [dbo].[InsertPosao]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[InsertPosao]
    ON [dbo].[Posao]
    FOR INSERT
    AS
    BEGIN
	Declare @idS ID,
			@idN ID,
			@idMag ID

		select @idS = IdSpr, @idN = IdNor
		from inserted
		
		if((not exists(Select * from Norma where IdNor = @idN)) or (not exists(select * from Sprat where IdSpr = @idS))) begin
			print 'ne postoji zadata norma ili zadati sprat'
			rollback transaction
			end
		else begin

			set @idMag = dbo.dohvatiMagacin(@idS)

			Declare @kursor cursor,
					@kolicina int,
					@status int,
					@idR ID
			
			set @kursor = cursor for
			select IdR, Kol_Jed 
			from Potrebna_Roba
			where IdNor = @idN
			
			open @kursor

			fetch next from @kursor into
			@idR, @kolicina

			while @@FETCH_STATUS = 0 begin
				execute @status = SPSmanjiRobu @IdMag, @IdR, @kolicina
				if(@status != @kolicina) begin
					print 'ne postoji dovoljno robe'
					rollback transaction
					break
				end
				fetch next from @kursor into
				@idR, @kolicina
			end

			close @kursor
			deallocate @kursor
			end
    END
GO
/****** Object:  Trigger [dbo].[UpdatePosao]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[UpdatePosao]
    ON [dbo].[Posao]
    FOR UPDATE
    AS
    BEGIN
	if(UPDATE(DatumKraja)) begin
		Declare @datumKraja date,
				@datumPocetka date,
				@idP ID,
				@idZap ID

		select @datumKraja = DatumKraja
		from deleted

		if(@datumKraja is not null) begin
			print 'Datum kraja je vec postavljen'
			rollback transaction
			return
		end

		select @datumKraja = DatumKraja, @datumPocetka = DatumPocetka, @idP = IdPos
		from inserted

		if(@datumKraja < @datumPocetka) begin
			print 'datum kraja ne moze biti manji od datuma pocetka'
			rollback transaction
			return
		end

		Declare @kursor cursor,
				@datumKRad date
		
		set @kursor = cursor for
		select DatumKraja
		from Radi_Posao
		where IdPos = @idP and DatumKraja is not null

		open @kursor

		fetch next from @kursor into @datumKRad

		while @@FETCH_STATUS = 0 begin
			if(@datumKRad > @datumKraja) begin
				print 'datum kraja posla ne moze biti manji od datuma kraja rada nekog Zaposlenog na tom poslu'
				rollback transaction
				close @kursor
				deallocate @kursor
				return
			end

			fetch next from @kursor into @datumKRad
		end

		set @kursor = cursor for
		select IdZap
		from Radi_Posao
		where IdPos = @idP and DatumKraja is null

		open @kursor

		fetch next from @kursor into @idZap

		while @@FETCH_STATUS = 0 begin
			update Radi_Posao
			set DatumKraja = @datumKraja
			where IdZap = @idZap and IdPos = @idP

			fetch next from @kursor into @idZap
		end


		close @kursor
		deallocate @kursor

		update Posao
		set Status = 'Z'
		where IdPos = @idP

		exec SPisplatiPosao @idP

		end
	else if(UPDATE(DatumPocetka)) begin
		select  @datumPocetka = DatumPocetka, @idP = IdPos
		from inserted

		if(not exists(select * from inserted where Status = 'U')) begin
			print 'posao nije u toku'
			rollback transaction
			end
		else begin
			Declare @kursorP cursor,
					@datumPRad date,
					@datumPoc date
		
		select @datumPoc = DatumPocetka
			from inserted
		
		set @kursorP = cursor for
		select DatumPocetka 
		from Radi_Posao
		where IdPos = @idP

		open @kursorP

		fetch next from @kursorP into @datumPRad

		while @@FETCH_STATUS = 0 begin
			
			if(@datumPRad < @datumPoc) begin
				print 'datum pocetka posla ne moze biti veci od datuma pocetka rada nekog zaposlenog na tom poslu'
				rollback transaction
				break
				end
			fetch next from @kursorP into @datumPRad
			end

			close @kursorP
			deallocate @kursorP
			end		
		end
    END
GO
/****** Object:  Trigger [dbo].[InsertPotrebnaRoba]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[InsertPotrebnaRoba]
    ON [dbo].[Potrebna_Roba]
    FOR INSERT
    AS
    BEGIN
	Declare @idR ID,
			@idN ID,
			@naziv varchar(50)

			select @idR = IdR, @idN = IdNor
			from inserted

		select @naziv = T.Naziv 
		from Roba R, Tip T
		where R.IdR = @IdR and R.IdTip = T.IdTip

		if(LOWER(@naziv) != 'materijal') begin
			print 'Roba mora biti materijal'
			rollback transaction
			end
		
    END
GO
/****** Object:  Trigger [dbo].[InsertRadiPosao]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[InsertRadiPosao]
	ON [dbo].[Radi_Posao]
    FOR INSERT
    AS
    BEGIN
		DECLARE @datumP date,
				@pocetakPosla date,
				@idP ID,
				@idZ ID
		
		select @datumP = DatumPocetka, @idP = IdPos, @idZ = IdZap
		from inserted
		
		select @pocetakPosla = DatumPocetka 
		from Posao 
		where IdPos = @idP and Status = 'U'

		if(@@ROWCOUNT = 0 or @datumP < @pocetakPosla) begin
			print 'Posao je zavrsen ili datum pocetka rada je manji od datuma pocetka posla'
			rollback transaction
			end
		else if(exists(Select IdZap 
					from Je_Zaposlen
					where IdZap = @IdZ )) begin
					print 'Zaposleni vec radi u magacinu'
					rollback transaction
					end

		else if(1 < (Select Count(R.IdZap)
					from Radi_Posao R, Posao P
					where R.IdZap = @IdZ and P.IdPos = R.IdPos and P.Status = 'U')) begin
					print 'Zaposleni ne moze da radi na 2 posla u isto vreme'
					rollback transaction
					end
			
    END
GO
/****** Object:  Trigger [dbo].[UpdateRadiPosao]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[UpdateRadiPosao]
	ON [dbo].[Radi_Posao]
    FOR UPDATE
    AS
    BEGIN
		

		if(Update(Ocena)) begin
			DECLARE @idZ ID

			select @idZ = IdZap
			from inserted

			exec SPRacunajProsek @idZ
		end
		else  begin 
		
		DECLARE @datumP date,
				@datumK date,
				@status int,
				@IdP ID
		
		select @datumP = DatumPocetka, @datumK = DatumKraja, @IdP = IdPos
				from inserted

		if (@datumP > @datumK) begin
			print 'Datum kraja ne moze biti manji od datuma pocetka'
			rollback transaction
			return
		end

		if(UPDATE(DatumKraja)) begin
					
			set @status = dbo.proveraDatumaRada(@datumK, @IdP)
			if(@status = -1) begin
				print 'Posao vise nije u toku ili datumi nisu valjani(svi datumi radnika moraju biti u okviru datuma posla)'
				rollback transaction
				end
		end

		else if(UPDATE(DatumPocetka)) begin
			set @status = dbo.proveraDatumaRada(@datumP,  @IdP)
			if(@status = -1) begin
				print 'Posao vise nije u toku ili datumi nisu valjani(svi datumi radnika moraju biti u okviru datuma posla)'
				rollback transaction
				end
		end
		end
    END
GO
/****** Object:  Trigger [dbo].[InsertRoba]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[InsertRoba]
    ON [dbo].[Roba]
    FOR INSERT
    AS
    BEGIN
		Declare @idT ID,
				@naziv varchar(50)

		Select @idT = IdTip from inserted

		Select @naziv = Naziv
		from Tip
		where IdTip = @idT


		print @naziv
		if(@naziv not in ('htz', 'alat', 'materijal')) begin
			print 'Zadati tip nije odgovarajuci'
			rollback transaction
			end

    END
GO
/****** Object:  Trigger [dbo].[DeleteSprat]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[DeleteSprat]
    ON [dbo].[Sprat]
    FOR DELETE
    AS
    BEGIN
	Declare @idO ID,
			@rbr int,
			@brSpratova int

			select @idO = IdObj, @rbr = RedniBroj
			from deleted

		select @brSpratova = BrojSpratova 
		from Objekat
		where IdObj = @idO

		if((@brSpratova - 1) != @rbr) begin
			print 'Ne moze se obrisati sprat koji nije na vrhu'
			rollback transaction
			end

		else
		update Objekat
		set BrojSpratova = BrojSpratova - 1
		where IdObj = @idO
		
    END
GO
/****** Object:  Trigger [dbo].[InsertSprat]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[InsertSprat]
    ON [dbo].[Sprat]
    FOR INSERT
    AS
    BEGIN
	Declare @idO ID,
			@rbr int,
			@brSpratova int

			select @idO = IdObj, @rbr = RedniBroj
			from inserted

		select @brSpratova = BrojSpratova 
		from Objekat
		where IdObj = @idO

		if(@brSpratova != @rbr) begin
			print 'Redni broj sprata nije validan'
			rollback transaction
			end

		else
		update Objekat
		set BrojSpratova = BrojSpratova + 1
		where IdObj = @idO
		
    END
GO
/****** Object:  Trigger [dbo].[JedinicaRobeUMagacinu]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[JedinicaRobeUMagacinu]
    ON [dbo].[U_Magacinu]
    FOR INSERT
    AS
    BEGIN
		Declare @naziv varchar(50),
				@jedMere char,
				@IdR ID,
				@idMag ID
		
		Select @jedMere = JedMere, @IdR = IdR, @idMag = IdMag
		from inserted


		if(@jedMere = 'K')
		begin
		select @naziv = T.Naziv 
		from Roba R, Tip T
		where R.IdR = @IdR and R.IdTip = T.IdTip

		if(LOWER(@naziv) != 'materijal') begin
			print 'Roba mora biti materijal'
			rollback transaction;
			end
		end
    END
GO
/****** Object:  Trigger [dbo].[DeleteZaduzenjeOpreme]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[DeleteZaduzenjeOpreme]
    ON [dbo].[Zaduzenje_Opreme]
    FOR DELETE
    AS
    BEGIN
		Declare 
				@IdZap ID,
				@IdZaduz ID,
				@kursor CURSOR

		set @kursor = CURSOR for
		Select IdZap, IdZaduz
		from deleted
		
		open @kursor

		fetch next from @kursor
		into  @IdZap, @IdZaduz

		while @@FETCH_STATUS = 0 begin
			if((select DatumRazduzenja
				from deleted
				where IdZaduz = @IdZaduz) is null) begin
			update Zaposleni
			set ZaduzenoOpreme = ZaduzenoOpreme - 1
			where IdZap = @IdZap
			end
			fetch next from @kursor
			into @IdZap, @IdZaduz

		end
	
    END
GO
/****** Object:  Trigger [dbo].[InsertZaduzenjeOpreme]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[InsertZaduzenjeOpreme]
    ON [dbo].[Zaduzenje_Opreme]
    FOR INSERT
    AS
    BEGIN
		Declare @naziv varchar(50),
				@IdZap ID,
				@IdR ID,
				@IdMag ID,
				@status int,
				@broj int
		
		Select @IdR = IdR, @IdZap = IdZap, @IdMag = IdMag
		from inserted
		
		/*select @broj = count(*)
		from Zaduzenje_Opreme
		where IdR = @IdR and IdZap = @IdZap and DatumRazduzenja IS NULL
		print @broj
		if(@broj > 1) begin
			print 'Moze se zaduziti samo po 1 primerak iste robe'
			rollback transaction
			return
		end*/
			
		select @naziv = T.Naziv 
		from Roba R, Tip T
		where R.IdR = @IdR and R.IdTip = T.IdTip

		if(LOWER(@naziv) = 'materijal') begin
			print 'Ne moze se zaduziti roba koja je materijal'
			rollback transaction;
			end
		else begin
			

			execute @status = SPSmanjiRobu @IdMag, @IdR, 1

			if(@status != -1) begin
			update Zaposleni
			set ZaduzenoOpreme = 1 + ZaduzenoOpreme
			where IdZap = @IdZap
			end
			else begin 
				print 'Zadata roba ne postoji u zadatom magacinu'
				rollback transaction
				
			end
		end
    END
GO
/****** Object:  Trigger [dbo].[UpdateZaduzenjeOpreme]    Script Date: 16.6.2017. 23:08:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[UpdateZaduzenjeOpreme]
    ON [dbo].[Zaduzenje_Opreme]
    FOR UPDATE
    AS
    BEGIN
	if(UPDATE(DatumRazduzenja)) begin

	declare @staridatumRazd date
	select @staridatumRazd = DatumRazduzenja
	from deleted
	
	if(@staridatumRazd is not null) begin
		print 'DatumRazduzenja je vec postavljen'
		rollback transaction;
		end
	else begin
		Declare 
				@IdZap ID,
				@IdR ID,
				@IdMag ID,
				@datumRazd date,
				@datumZad date
		
		Select @datumRazd= DatumRazduzenja, @datumZad = DatumZaduzenja, @IdZap = IdZap, @IdMag = IdMag, @IdR = IdR
		from inserted
		
		if(@datumRazd < @datumZad) begin
		print 'Datum razduzenja ne moze biti manji od datuma zaduzenja'
		rollback transaction
		end
		else begin
		
		execute SPUvecajRobu @IdMag, @IdR, 1, 'J'

			update Zaposleni
			set ZaduzenoOpreme = ZaduzenoOpreme - 1
			where IdZap = @IdZap
		end
		end
		end
    END
GO
USE [master]
GO
ALTER DATABASE [GradjevinskaFirma] SET  READ_WRITE 
GO
