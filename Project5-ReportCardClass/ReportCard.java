/*This class represents a report card with 5 courses.
 *The grades can have disrete values from 1-10 only. */

public class ReportCard{

  /*Individual grades for courses*/
  private int mHistoryGrade;
  private int mPhysicsGrade;
  private int mMathsGrade;
  private int mEnglishGrade;
  private int mChemistryGrade;

  /*Constructor*/
  public ReportCard(int historyGrade,int physicsGrade,int mathsGrade,int englishGrade,int chemistryGrade){
    mHistoryGrade = historyGrade;
    mPhysicsGrade = physicsGrade;
    mMathsGrade = mathsGrade;
    mEnglishGrade = englishGrade;
    mChemistryGrade = chemistryGrade;
  }

  /*Setters for the individual course grades*/
  public void setHistoryGrade(int historyGrade){
    mHistoryGrade = historyGrade;
  }

  public void setPhysicsgrade(int physicsGrade){
    mPhysicsGrade = physicsGrade;
  }

  public void setMathsGrade(int mathsGrade){
    mMathsGrade = mathsGrade;
  }

  public void setEnglishGrade(int englishGrade){
    mEnglishGrade = englishGrade;
  }

  public void setChemistryGrade(int chemistryGrade){
    mChemistryGrade = chemistryGrade;
  }

  /*Getters for the individual course grades*/
  public int getHistoryGrade(){
    return mHistoryGrade;
  }

  public int getPhysicsgrade(){
    return mPhysicsGrade;
  }

  public int getMathsGrade(){
    return mMathsGrade;
  }

  public int getEnglishGrade(){
    return mEnglishGrade;
  }

  public int getChemistryGrade(){
    return mChemistryGrade;
  }

  /*Get the average from the course grades as a float*/
  public float getAverage(){
    float average = (float) (mHistoryGrade + mPhysicsGrade + mMathsGrade + mEnglishGrade + mChemistryGrade) / 5;
    return average;
  }

  /*To String method*/
  @Override
  public String toString(){
    return "The student's grades in each course are as follows:" +
            "\nHistory:  " + mHistoryGrade +
            "\nPhysics:  " + mPhysicsGrade +
            "\nMaths:    " + mMathsGrade   +
            "\nEnglish:  " + mEnglishGrade +
            "\nChemistry:" + mChemistryGrade +
            "\nWith an average score of " + this.getAverage() + ".";
  }

}
