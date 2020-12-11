package com.ericsson.colorful;

import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.CodeException;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantPool;

import edu.umd.cs.findbugs.BugInstance;
import edu.umd.cs.findbugs.BugReporter;
import edu.umd.cs.findbugs.bcel.PreorderDetector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NullPointerExceptionDetector extends PreorderDetector {
  private final BugReporter bugReporter;

  public NullPointerExceptionDetector(BugReporter bugReporter) {
    this.bugReporter = bugReporter;
  }

  private void reportError(CodeException obj) {
    BugInstance bug =
      new BugInstance(this, "ECD_NULLPOINTER_EXCEPTION", NORMAL_PRIORITY)
        .addClassAndMethod(this)
        .addSourceLine(this.getClassContext(), this, obj.getStartPC());
    bugReporter.reportBug(bug);
  }

  @Override
  public void visit(CodeException exceptionBlock) {
    ConstantPool pool = getConstantPool();
    int type = exceptionBlock.getCatchType();
    if (type == 0) return;

    String exceptionName = pool.constantToString(pool.getConstant(type));
    
    List<String> illegalExceptions = new ArrayList<>(
      List.of(
        "java.lang.NullPointerException",
        "java.lang.RuntimeException",
        "java.lang.Exception",
        "java.lang.Throwable"
      )
    );
    if (illegalExceptions.contains(exceptionName))
      reportError(exceptionBlock);
  }
}
