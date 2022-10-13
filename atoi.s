  .syntax unified
  .cpu cortex-m3
  .fpu softvfp
  .thumb
  
  .global  Main

Main:

@ Follow the instructions in the handout for Assignment 2


  @Hexidecimal
 CMP R4, #'9'
 BHI hex
 CMP R3, #'9'
 BHI hex
 CMP R2, #'9'
 BHI hex
 CMP R1, #'9'
 BHI hex

 @ Store hex value
  MOV R6, #0x30    
hex: 
  MOV R6, #0x37  @Hexidecimal
  LDR R5, =16


@Take hex values from ascii chars
  SUB R1, R1, R6    
  SUB R2, R2, R6
  SUB R3, R3, R6
  SUB R4, R4, R6

  @Calculations
  MUL R4, R5    @MOST SIG CUBED
    MUL R4, R5
     MUL R4, R5

  MUL R3, R5    @SCND MOST SQ
    MUL R3, R5

  MUL R2, R5

  @Adds calcs
  ADD R7, R4, R3
  ADD R8, R2, R1
  ADD R0, R7, R8

  

  @ End of program ... check your result
  

End_Main:
  BX    LR

  .end