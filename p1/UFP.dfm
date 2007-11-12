object GLForm2D: TGLForm2D
  Left = 306
  Top = 157
  Width = 408
  Height = 450
  Caption = 'Práctica 1: Rectángulo Aúreo'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  Menu = MainMenu1
  OldCreateOrder = False
  OnActivate = CColorGrid1Change
  OnCreate = FormCreate
  OnDestroy = FormDestroy
  OnKeyDown = FormKeyDown
  OnKeyPress = FormKeyPress
  OnPaint = FormPaint
  OnResize = FormResize
  PixelsPerInch = 96
  TextHeight = 13
  object CColorGrid1: TCColorGrid
    Left = 8
    Top = 375
    Width = 384
    Height = 25
    Cursor = crHandPoint
    Anchors = [akLeft, akRight, akBottom]
    ClickEnablesColor = True
    GridOrdering = go16x1
    ForegroundEnabled = False
    BackgroundEnabled = False
    TabOrder = 0
    Visible = False
    OnChange = CColorGrid1Change
  end
  object Panel1: TPanel
    Left = 0
    Top = 0
    Width = 400
    Height = 49
    Align = alTop
    Anchors = [akLeft, akRight]
    BevelOuter = bvNone
    Caption = 'Panel1'
    Color = clBlack
    FullRepaint = False
    TabOrder = 1
    Visible = False
    object Label1: TLabel
      Left = 8
      Top = 8
      Width = 88
      Height = 19
      Anchors = []
      Caption = 'Punto Centro'
      Color = clBlack
      Font.Charset = ANSI_CHARSET
      Font.Color = clRed
      Font.Height = -16
      Font.Name = 'Times New Roman'
      Font.Style = [fsBold]
      ParentColor = False
      ParentFont = False
    end
    object Label3: TLabel
      Left = 112
      Top = 8
      Width = 88
      Height = 19
      Anchors = []
      Caption = 'Punto Origen'
      Color = clBlack
      Font.Charset = ANSI_CHARSET
      Font.Color = clYellow
      Font.Height = -16
      Font.Name = 'Times New Roman'
      Font.Style = [fsBold]
      ParentColor = False
      ParentFont = False
    end
    object Label4: TLabel
      Left = 216
      Top = 8
      Width = 83
      Height = 19
      Anchors = []
      Caption = 'Rectángulos'
      Color = clBlack
      Font.Charset = ANSI_CHARSET
      Font.Color = clLime
      Font.Height = -16
      Font.Name = 'Times New Roman'
      Font.Style = [fsBold]
      ParentColor = False
      ParentFont = False
    end
    object Label5: TLabel
      Left = 312
      Top = 8
      Width = 75
      Height = 19
      Anchors = []
      Caption = 'Diagonales'
      Color = clBlack
      Font.Charset = ANSI_CHARSET
      Font.Color = clFuchsia
      Font.Height = -16
      Font.Name = 'Times New Roman'
      Font.Style = [fsBold]
      ParentColor = False
      ParentFont = False
    end
    object ScrollBar1: TScrollBar
      Left = 8
      Top = 32
      Width = 88
      Height = 16
      Anchors = []
      Min = 1
      PageSize = 0
      Position = 3
      TabOrder = 0
      TabStop = False
      OnChange = ScrollBar1Change
    end
    object ScrollBar2: TScrollBar
      Left = 112
      Top = 32
      Width = 88
      Height = 16
      Anchors = []
      PageSize = 0
      TabOrder = 1
      TabStop = False
      OnChange = ScrollBar2Change
    end
    object ScrollBar3: TScrollBar
      Left = 216
      Top = 32
      Width = 80
      Height = 16
      Anchors = []
      Max = 20
      Min = 1
      PageSize = 0
      Position = 1
      TabOrder = 2
      TabStop = False
      OnChange = ScrollBar3Change
    end
    object ScrollBar4: TScrollBar
      Left = 312
      Top = 32
      Width = 81
      Height = 16
      Anchors = []
      Max = 20
      Min = 1
      PageSize = 0
      Position = 1
      TabOrder = 3
      TabStop = False
      OnChange = ScrollBar4Change
    end
  end
  object Panel2: TPanel
    Left = 0
    Top = 224
    Width = 129
    Height = 145
    Anchors = [akLeft, akBottom]
    BevelOuter = bvNone
    Caption = 'Panel2'
    Color = clBtnText
    TabOrder = 2
    Visible = False
    object RadioButton2: TRadioButton
      Left = 8
      Top = 32
      Width = 113
      Height = 17
      Caption = 'Punto Límite'
      Color = clBlack
      Font.Charset = ANSI_CHARSET
      Font.Color = clWhite
      Font.Height = -15
      Font.Name = 'Times New Roman'
      Font.Style = [fsBold]
      ParentColor = False
      ParentFont = False
      TabOrder = 0
      OnKeyPress = RadioButton2KeyPress
    end
    object RadioButton1: TRadioButton
      Left = 8
      Top = 8
      Width = 113
      Height = 17
      Caption = 'Punto Origen'
      Color = clBlack
      Font.Charset = ANSI_CHARSET
      Font.Color = clWhite
      Font.Height = -15
      Font.Name = 'Times New Roman'
      Font.Style = [fsBold]
      ParentColor = False
      ParentFont = False
      ParentShowHint = False
      ShowHint = True
      TabOrder = 1
      OnKeyPress = RadioButton1KeyPress
    end
    object RadioButton3: TRadioButton
      Left = 8
      Top = 56
      Width = 113
      Height = 17
      Caption = 'Rectángulo'
      Color = clBlack
      Font.Charset = ANSI_CHARSET
      Font.Color = clWhite
      Font.Height = -15
      Font.Name = 'Times New Roman'
      Font.Style = [fsBold]
      ParentColor = False
      ParentFont = False
      ParentShowHint = False
      ShowHint = True
      TabOrder = 2
      OnKeyPress = RadioButton3KeyPress
    end
    object RadioButton4: TRadioButton
      Left = 8
      Top = 80
      Width = 113
      Height = 17
      Caption = 'Diagonales'
      Color = clBlack
      Font.Charset = ANSI_CHARSET
      Font.Color = clWhite
      Font.Height = -15
      Font.Name = 'Times New Roman'
      Font.Style = [fsBold]
      ParentColor = False
      ParentFont = False
      ParentShowHint = False
      ShowHint = True
      TabOrder = 3
      OnKeyPress = RadioButton4KeyPress
    end
    object RadioButton5: TRadioButton
      Left = 8
      Top = 128
      Width = 113
      Height = 17
      Caption = 'Fondo'
      Checked = True
      Color = clBlack
      Font.Charset = ANSI_CHARSET
      Font.Color = clWhite
      Font.Height = -15
      Font.Name = 'Times New Roman'
      Font.Style = [fsBold]
      ParentColor = False
      ParentFont = False
      ParentShowHint = False
      ShowHint = True
      TabOrder = 4
      TabStop = True
      OnKeyPress = RadioButton5KeyPress
    end
    object RadioButton6: TRadioButton
      Left = 8
      Top = 104
      Width = 113
      Height = 17
      Caption = 'Actual'
      Color = clBlack
      Font.Charset = ANSI_CHARSET
      Font.Color = clWhite
      Font.Height = -15
      Font.Name = 'Times New Roman'
      Font.Style = [fsBold]
      ParentColor = False
      ParentFont = False
      ParentShowHint = False
      ShowHint = True
      TabOrder = 5
      OnKeyPress = RadioButton6KeyPress
    end
  end
  object MainMenu1: TMainMenu
    object Menu1: TMenuItem
      Caption = '&Opciones'
      object Comandos1: TMenuItem
        Caption = '&Color'
        OnClick = Comandos1Click
      end
      object GrosorPunto1: TMenuItem
        Caption = '&Grosor'
        OnClick = GrosorPunto1Click
      end
      object Embaldosar1: TMenuItem
        Caption = '&Embaldosar'
        OnClick = Embaldosar1Click
      end
      object Salir1: TMenuItem
        Caption = '&Salir'
        OnClick = Salir1Click
      end
    end
    object Creditos1: TMenuItem
      Caption = '&Ayuda'
      object Comandos2: TMenuItem
        Caption = '&Comandos'
        OnClick = Comandos2Click
      end
      object Autores1: TMenuItem
        Caption = '&Autores'
        OnClick = Autores1Click
      end
    end
  end
end
