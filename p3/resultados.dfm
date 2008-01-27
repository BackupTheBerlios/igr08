object Form1: TForm1
  Left = 195
  Top = 144
  Width = 307
  Height = 239
  Caption = 'Form1'
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  PixelsPerInch = 96
  TextHeight = 13
  object LabelPuntos: TLabel
    Left = 16
    Top = 56
    Width = 84
    Height = 36
    Caption = 'Puntos'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -32
    Font.Name = 'Times New Roman'
    Font.Style = []
    ParentFont = False
  end
  object LabelVidas: TLabel
    Left = 16
    Top = 24
    Width = 72
    Height = 13
    Caption = 'Vidas restantes'
  end
  object Puntos: TEdit
    Left = 72
    Top = 96
    Width = 153
    Height = 65
    BorderStyle = bsNone
    Color = clBtnFace
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clBlue
    Font.Height = -67
    Font.Name = 'Times New Roman'
    Font.Style = [fsBold, fsItalic]
    ParentFont = False
    ParentShowHint = False
    ShowHint = False
    TabOrder = 0
    Text = '0'
  end
  object Vidas: TEdit
    Left = 96
    Top = 24
    Width = 121
    Height = 21
    BorderStyle = bsNone
    Color = clBtnFace
    ReadOnly = True
    TabOrder = 1
    Text = '3'
  end
end
