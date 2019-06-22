USE [spring_mybatis]
GO
drop table baseuser;
drop table district;

/****** Object:  Table [dbo].[baseuser]    Script Date: 2019-05-11 09:42:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[baseuser](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[age] [int] NULL,
	[sex] [nvarchar](10) NULL,
 CONSTRAINT [PK_user] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[district]    Script Date: 2019-05-11 09:42:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[district](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[districtName] [nvarchar](50) NULL,
	[createTime] [datetime] NULL,
	[isInvalid] [smallint] NULL,
 CONSTRAINT [PK_spring_mybatis_district] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
ALTER TABLE [dbo].[district] ADD  CONSTRAINT [DF_spring_mybatis_district_createTime]  DEFAULT (getdate()) FOR [createTime]
GO
ALTER TABLE [dbo].[district] ADD  CONSTRAINT [DF_spring_mybatis_district_isInvalid]  DEFAULT ((1)) FOR [isInvalid]
GO



INSERT INTO [dbo].[district]( [districtName], [createTime], [isInvalid]) VALUES ( N'电子', '2019-05-10 09:49:07.320', 1);
INSERT INTO [dbo].[district]( [districtName], [createTime], [isInvalid]) VALUES ( N'干区', '2019-05-10 09:49:11.703', 1);
INSERT INTO [dbo].[district]( [districtName], [createTime], [isInvalid]) VALUES ( N'湿区', '2019-05-10 09:49:14.953', 1);

INSERT INTO [dbo].[baseuser]( [name], [age], [sex]) VALUES ( N'刘丽', 18, N'女');
INSERT INTO [dbo].[baseuser]( [name], [age], [sex]) VALUES ( N'杨颖', 19, N'女');
INSERT INTO [dbo].[baseuser]( [name], [age], [sex]) VALUES ( N'迪丽热巴', 20, N'女');
INSERT INTO [dbo].[baseuser]( [name], [age], [sex]) VALUES ( N'古力娜扎', 21, N'女');






