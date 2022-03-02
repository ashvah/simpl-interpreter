/* The following code was generated by JFlex 1.4.3 on 2021/6/13 ����4:30 */

package simpl.parser;

import java_cup.runtime.Symbol;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 2021/6/13 ����4:30 from the specification file
 * <tt>simpl.lex</tt>
 */
class Lexer implements java_cup.runtime.Scanner, Symbols {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYCOMMENT = 2;
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\6\1\1\1\0\1\6\1\2\22\0\1\6\1\46\3\0"+
    "\1\40\1\0\1\4\1\7\1\11\1\10\1\35\1\47\1\36\1\0"+
    "\1\37\12\5\1\45\1\50\1\43\1\42\1\44\2\0\32\4\4\0"+
    "\1\3\1\0\1\30\1\3\1\20\1\22\1\16\1\17\1\3\1\23"+
    "\1\13\1\34\1\3\1\14\1\32\1\12\1\26\1\33\1\3\1\15"+
    "\1\24\1\21\1\27\1\3\1\25\1\3\1\31\1\3\1\51\1\0"+
    "\1\52\1\41\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\2\2\1\3\1\4\1\5\1\6\1\7"+
    "\15\3\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\1\1\20\1\21\1\22\1\23\1\24\3\25"+
    "\1\26\1\27\1\30\2\3\1\31\1\32\4\3\1\33"+
    "\5\3\1\34\5\3\1\35\1\36\1\37\1\40\1\41"+
    "\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51"+
    "\1\52\14\3\1\53\2\3\1\54\1\55\6\3\1\56"+
    "\1\57\1\60\1\61\2\3\1\62\2\3\1\63\1\64"+
    "\1\65\1\3\1\66";

  private static int [] zzUnpackAction() {
    int [] result = new int[114];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\53\0\126\0\126\0\201\0\254\0\327\0\u0102"+
    "\0\u012d\0\126\0\u0158\0\u0183\0\u01ae\0\u01d9\0\u0204\0\u022f"+
    "\0\u025a\0\u0285\0\u02b0\0\u02db\0\u0306\0\u0331\0\u035c\0\126"+
    "\0\126\0\126\0\126\0\126\0\u0387\0\u03b2\0\u03dd\0\u0408"+
    "\0\126\0\126\0\126\0\126\0\126\0\126\0\u0433\0\u045e"+
    "\0\126\0\126\0\126\0\u0489\0\u04b4\0\254\0\254\0\u04df"+
    "\0\u050a\0\u0535\0\u0560\0\254\0\u058b\0\u05b6\0\u05e1\0\u060c"+
    "\0\u0637\0\254\0\u0662\0\u068d\0\u06b8\0\u06e3\0\u070e\0\126"+
    "\0\126\0\126\0\126\0\126\0\126\0\126\0\126\0\254"+
    "\0\254\0\254\0\254\0\254\0\254\0\u0739\0\u0764\0\u078f"+
    "\0\u07ba\0\u07e5\0\u0810\0\u083b\0\u0866\0\u0891\0\u08bc\0\u08e7"+
    "\0\u0912\0\254\0\u093d\0\u0968\0\254\0\254\0\u0993\0\u09be"+
    "\0\u09e9\0\u0a14\0\u0a3f\0\u0a6a\0\254\0\254\0\254\0\254"+
    "\0\u0a95\0\u0ac0\0\254\0\u0aeb\0\u0b16\0\254\0\254\0\254"+
    "\0\u0b41\0\254";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[114];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\6\1\3\1\7\1\4\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20"+
    "\1\6\1\21\1\22\1\6\1\23\1\24\1\25\1\6"+
    "\1\26\2\6\1\27\1\6\1\30\1\31\1\32\1\33"+
    "\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43"+
    "\1\44\1\45\7\46\1\47\1\50\42\46\54\0\1\4"+
    "\54\0\3\6\4\0\23\6\23\0\1\7\55\0\1\51"+
    "\1\52\52\0\1\53\44\0\3\6\4\0\1\6\1\54"+
    "\12\6\1\55\6\6\21\0\3\6\4\0\1\56\4\6"+
    "\1\57\15\6\21\0\3\6\4\0\4\6\1\60\16\6"+
    "\21\0\3\6\4\0\4\6\1\61\16\6\21\0\3\6"+
    "\4\0\1\62\1\6\1\63\20\6\21\0\3\6\4\0"+
    "\1\64\13\6\1\65\1\6\1\66\4\6\21\0\3\6"+
    "\4\0\3\6\1\67\5\6\1\70\11\6\21\0\3\6"+
    "\4\0\4\6\1\71\7\6\1\72\6\6\21\0\3\6"+
    "\4\0\7\6\1\73\13\6\21\0\3\6\4\0\11\6"+
    "\1\74\11\6\21\0\3\6\4\0\3\6\1\75\17\6"+
    "\21\0\3\6\4\0\1\76\22\6\21\0\3\6\4\0"+
    "\3\6\1\77\17\6\62\0\1\100\50\0\1\101\1\0"+
    "\1\102\50\0\1\103\52\0\1\104\2\0\1\105\15\0"+
    "\1\106\53\0\1\107\44\0\3\6\4\0\2\6\1\110"+
    "\20\6\21\0\3\6\4\0\7\6\1\111\13\6\21\0"+
    "\3\6\4\0\7\6\1\112\13\6\21\0\3\6\4\0"+
    "\5\6\1\113\1\114\14\6\21\0\3\6\4\0\10\6"+
    "\1\115\12\6\21\0\3\6\4\0\12\6\1\116\10\6"+
    "\21\0\3\6\4\0\3\6\1\117\17\6\21\0\3\6"+
    "\4\0\2\6\1\120\20\6\21\0\3\6\4\0\15\6"+
    "\1\121\5\6\21\0\3\6\4\0\4\6\1\122\16\6"+
    "\21\0\3\6\4\0\2\6\1\123\2\6\1\124\15\6"+
    "\21\0\3\6\4\0\3\6\1\125\17\6\21\0\3\6"+
    "\4\0\1\6\1\126\21\6\21\0\3\6\4\0\4\6"+
    "\1\127\16\6\21\0\3\6\4\0\10\6\1\130\12\6"+
    "\21\0\3\6\4\0\14\6\1\131\6\6\21\0\3\6"+
    "\4\0\4\6\1\132\16\6\21\0\3\6\4\0\6\6"+
    "\1\133\14\6\21\0\3\6\4\0\12\6\1\134\10\6"+
    "\21\0\3\6\4\0\4\6\1\135\16\6\21\0\3\6"+
    "\4\0\1\136\22\6\21\0\3\6\4\0\16\6\1\137"+
    "\4\6\21\0\3\6\4\0\1\6\1\140\21\6\21\0"+
    "\3\6\4\0\4\6\1\141\16\6\21\0\3\6\4\0"+
    "\2\6\1\142\20\6\21\0\3\6\4\0\2\6\1\143"+
    "\20\6\21\0\3\6\4\0\16\6\1\144\4\6\21\0"+
    "\3\6\4\0\22\6\1\145\21\0\3\6\4\0\4\6"+
    "\1\146\16\6\21\0\3\6\4\0\4\6\1\147\16\6"+
    "\21\0\3\6\4\0\17\6\1\150\3\6\21\0\3\6"+
    "\4\0\1\151\22\6\21\0\3\6\4\0\16\6\1\152"+
    "\4\6\21\0\3\6\4\0\4\6\1\153\16\6\21\0"+
    "\3\6\4\0\12\6\1\154\10\6\21\0\3\6\4\0"+
    "\2\6\1\155\20\6\21\0\3\6\4\0\4\6\1\156"+
    "\16\6\21\0\3\6\4\0\20\6\1\157\2\6\21\0"+
    "\3\6\4\0\4\6\1\160\16\6\21\0\3\6\4\0"+
    "\12\6\1\161\10\6\21\0\3\6\4\0\14\6\1\162"+
    "\6\6\16\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2924];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\2\11\5\1\1\11\15\1\5\11\4\1\6\11"+
    "\2\1\3\11\24\1\10\11\53\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[114];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    private int commentCount = 0;
    
    private Symbol token(int tag) {
        return new Symbol(tag, yyline, yycolumn);
    }
    
    private Symbol token(int tag, Object value) {
        return new Symbol(tag, yyline, yycolumn, value);
    }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Lexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 126) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 2: 
          { /* skip */
          }
        case 55: break;
        case 44: 
          { return token(TRUE);
          }
        case 56: break;
        case 37: 
          { return token(NIL);
          }
        case 57: break;
        case 19: 
          { return token(LBRACE);
          }
        case 58: break;
        case 26: 
          { return token(IF);
          }
        case 59: break;
        case 43: 
          { return token(ELSE);
          }
        case 60: break;
        case 25: 
          { return token(IN);
          }
        case 61: break;
        case 45: 
          { return token(THEN);
          }
        case 62: break;
        case 13: 
          { return token(EQ);
          }
        case 63: break;
        case 53: 
          { return token(ORELSE);
          }
        case 64: break;
        case 33: 
          { return token(ASSIGN);
          }
        case 65: break;
        case 16: 
          { return token(DEREF);
          }
        case 66: break;
        case 50: 
          { return token(WHILE);
          }
        case 67: break;
        case 6: 
          { return token(MUL);
          }
        case 68: break;
        case 1: 
          { throw new SyntaxError("Illegal character " + yytext(), yyline, yycolumn);
          }
        case 69: break;
        case 31: 
          { return token(NE);
          }
        case 70: break;
        case 54: 
          { return token(ANDALSO);
          }
        case 71: break;
        case 24: 
          { throw new SyntaxError("Comment mismatch, extra *) found", yyline, yycolumn);
          }
        case 72: break;
        case 18: 
          { return token(SEMI);
          }
        case 73: break;
        case 41: 
          { return token(REC);
          }
        case 74: break;
        case 32: 
          { return token(GE);
          }
        case 75: break;
        case 30: 
          { return token(LE);
          }
        case 76: break;
        case 5: 
          { return token(LPAREN);
          }
        case 77: break;
        case 8: 
          { return token(ADD);
          }
        case 78: break;
        case 42: 
          { return token(END);
          }
        case 79: break;
        case 20: 
          { return token(RBRACE);
          }
        case 80: break;
        case 17: 
          { return token(COMMA);
          }
        case 81: break;
        case 51: 
          { return token(DEFINE);
          }
        case 82: break;
        case 38: 
          { return token(NOT);
          }
        case 83: break;
        case 34: 
          { return token(CONS);
          }
        case 84: break;
        case 48: 
          { return token(FALSE);
          }
        case 85: break;
        case 10: 
          { return token(DIV);
          }
        case 86: break;
        case 47: 
          { return token(FORCE);
          }
        case 87: break;
        case 15: 
          { return token(GT);
          }
        case 88: break;
        case 22: 
          { commentCount = 1; yybegin(YYCOMMENT);
          }
        case 89: break;
        case 14: 
          { return token(LT);
          }
        case 90: break;
        case 11: 
          { return token(MOD);
          }
        case 91: break;
        case 4: 
          { return token(NUM, Integer.valueOf(yytext()));
          }
        case 92: break;
        case 3: 
          { return token(ID, yytext());
          }
        case 93: break;
        case 9: 
          { return token(SUB);
          }
        case 94: break;
        case 36: 
          { commentCount--; if (commentCount == 0) yybegin(YYINITIAL);
          }
        case 95: break;
        case 35: 
          { commentCount++;
          }
        case 96: break;
        case 52: 
          { return token(STREAM);
          }
        case 97: break;
        case 7: 
          { return token(RPAREN);
          }
        case 98: break;
        case 27: 
          { return token(FN);
          }
        case 99: break;
        case 46: 
          { return token(PROJ);
          }
        case 100: break;
        case 12: 
          { return token(NEG);
          }
        case 101: break;
        case 28: 
          { return token(DO);
          }
        case 102: break;
        case 23: 
          { return token(UNIT);
          }
        case 103: break;
        case 49: 
          { return token(DELAY);
          }
        case 104: break;
        case 40: 
          { return token(REF);
          }
        case 105: break;
        case 29: 
          { return token(ARROW);
          }
        case 106: break;
        case 39: 
          { return token(LET);
          }
        case 107: break;
        case 21: 
          { 
          }
        case 108: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              {     if (yystate() == YYCOMMENT) {
        throw new SyntaxError("Comment mismatch, need *) at EOF", yyline, yycolumn);
    }
    return token(EOF, null);
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
