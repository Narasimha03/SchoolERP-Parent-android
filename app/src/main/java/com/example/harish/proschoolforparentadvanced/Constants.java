package com.example.harish.proschoolforparentadvanced;


/**
 * Created by Suresh on 06-05-2017.
 */

public class Constants {


    public static final String schoolId = "SCH-9274";
    public static final String schoolIdPref = "schoolPref";
    public static final String rolePref = "role";
    public static final String empUniqueId = "uniqueId";
    public static final String tokenPref = "token";


    public static final String empId = "employeeId";
    public static final String subjectId = "subjectId";

    public static final String studentId = "studentId";
    public static final String employeeId = "employeeId";

    public static final String schoolIdInfo = "schoolIdInfo";


   // private static final String baseUrl = "http://192.168.1.5:4005/api/";

    private static final String baseUrl = "http://ec2-52-40-213-254.us-west-2.compute.amazonaws.com:4005/api/";
 public static final String loginUrl = "http://ec2-52-40-213-254.us-west-2.compute.amazonaws.com:4005/signin";
    //static final String loginUrl = "http://192.168.1.5:4005/signin";

    public static final String classUrl = baseUrl + "school_classes/";
    static final String sectionUrl = baseUrl + "class_sections/";
    static final String searchStdUrl = baseUrl + "search_student/";
    //:academic_year/:class_id/:section/:search_key
    static final String stdAddUrl = baseUrl + "students/";
    public static String studentProfile = baseUrl + "student_details/";//:student_id
    public static String employeeProfile = baseUrl + "employee_details/";//:student_id
    public static final String empAttByMonth = baseUrl + "employee_attendance_by_month/";//month//:emp_id//:school_id


    static final String listStdUrl = baseUrl + "students/"; //:section_id
    static final String busRouteUrl = baseUrl + "bus_route/";
    static final String empSearchUrl = baseUrl + "search_employee/";
    //:job_category/:gender/:search_key
    public static final String empAttendanceByCategory = baseUrl + "employee_Attendance_by_category/";//:emp_category://select_date//:school_id
    static final String listEmpUrl = baseUrl + "employee/";//:school_id
    public static final String listEmpAttUrl = baseUrl + "employees_by_school_id_category/";//job_category//:school_id
    public static String examMarksBulk = baseUrl + "marksbulk_eval/";

    static final String empAddUrl = baseUrl + "employee/";

    static final String addSubjectUrl = baseUrl + "subjects/"; //:section_id
    public  static final String subjectUrl = baseUrl + "subjects/"; //:section_id
    static final String addChapterUrl = baseUrl + "course_works/"; //:subject_id
    public  static final String chapterUrl = baseUrl + "course_works/"; //:subject_id
    static final String listTeacherUrl = baseUrl + "teachers/"; //:school_id
    static final String assignTeacherUrl = baseUrl + "add_subjects_to_teacher/"; //teacher_id
    static final String addAssignmentUrl = baseUrl + "assignment/";
    //:section_id/:lesson_id
   public static final String listAssignmentUrl = baseUrl + "assignment/";
    static final String addExamUrl = baseUrl + "exam_schedule/";//:school_id
   public static final String listExamUrl = baseUrl + "exam_schedule/"; //school_id
   public static final String listExamPaperUrl = baseUrl + "examsbysectionid/"; //:paper_id/:exam_sch_id
    public static final String addExamPaperUrl = baseUrl + "exams/"; //:subject_id/:exam_sch_id/:class_id/:section_id
    public static final String addEvalMarkUrl = baseUrl + "exam_eval/"; //:exam_paper_id/:student_id
    public static final String listEvalMarkurl = baseUrl + "exam_eval/"; //:exam_paper_id/:student_id
    static final String addBookUrl = baseUrl + "book/"; //:school_id
   public static final String listBookUrl = baseUrl + "book/"; //:school_id
    static final String listStationUrl = baseUrl + "transport_stations/"; //:school_id
    static final String addStationUrl = baseUrl + "transport_stations/"; //  :school_id
    static final String listStationRouteUrl = baseUrl + "bus_route_title/"; //:school_id
    static final String addBusRouteUrl = baseUrl + "bus_route_title/"; //:school_id
    static final String addVehicleUrl = baseUrl + "vehicles/";//:school_id
    static final String allVehicleUrl = baseUrl + "vehicle_all_details/";//:school_id
    static final String singleSearchVehicle = baseUrl + "vehicle_details/";//:vehicle_id
    public static final String deleteVehicle = baseUrl + "delete_vehicle/";//:vehicle_id


    static final int MULTIPLE_PERMISSIONS = 1;
    static final String timeTableUrl = baseUrl + "class_timetables/";//:section_id;
    static final String addTimeTableUrl = baseUrl + "class_timetable/";//:section_id//:subject_id;
    static final String classTimeTableByDay = baseUrl + "classes_timetable_by_day/";//:select_day/


    public static String allFeeUrl = baseUrl + "fee_types/";//:school_id
    public static String feeTypeByClassId = baseUrl + "feeTypes_by_classId/";//:class_id
    public static String feeAmountByFeeType = baseUrl + "fee_amount_by_fee_type/";//:fee_type_id

    public static String addFeeTypeUrl = baseUrl + "fee_types/";
    public static String allFeeMasterUrl = baseUrl + "fee_master/";//:school_id
    public static String addFeeMasterUrl = baseUrl + "fee_master/";//:school_id
    public static String addFeeCollectionUrl = baseUrl + "fee_collection/";//:student_id
    public static String allFeeCollectionUrl = baseUrl + "fee_collection/";
    public static String allFeeCollectionReportsByDay = baseUrl + "fee_by_Date/"; //select_date://school_id

    public static String allFeeCollectionReports = baseUrl + "section_student_fee_paid_details/"; //section_id://fee_types_id

    public static String studentAttandance = baseUrl + "attendancebulk/";//:class_id//:section_id//:school_id
    public static String employeeAttBulk = baseUrl + "employee_attendancebulk/";//:school_id

    public static String attendancechartbyday = baseUrl + "attendancechartbydate/";//:select_date//:class_id/section_id
    public static String attendancechartByDateAndStudent = baseUrl + "attendancechartbyStudentAndDate/";//:select_date//:student_id/
    public static String studentAttendanceGraph = baseUrl + "allClasses_Attendence_by_date/";//:date//:class_id/school_id

    public static String attendancechartbymonth = baseUrl + "attendancechartbymonth/";//:select_month//:student_id
    public static String schoolEvents = baseUrl + "schoolevents/";//:school_id
    public static String noticeBoardUrl = baseUrl + "noticeboard/";//:school_id
    public static String singleImage = baseUrl + "image/";//:file_name

    public static String sessionTimingUrl = baseUrl + "session_timings/";//:school_id
    public static String timetableDayUrl = baseUrl + "day_timetable_by_classId/";//:dayid//classid
    public static String timetableclassSectionUrl = baseUrl + "section_timetable_by_sectionId_for_android/";//:class_id//section_id


    public static String wordQuoteUrl = baseUrl + "quote_word/";//:school_id
    public static String allStudentAttendanceGraph = baseUrl + "section_monthly_attendence/";//:month//:section_id
    public static String showEventsUrl = baseUrl + "schooleventsByDate/";//:date//:school_id
    public static String singleAttendanceGraphURL = baseUrl + "student_tillDate_attendence/";//:student_id

    public static String empAttByAllMonths = baseUrl + "employee_tillDate_attendence/";//:employee_id

    public static String AllSectionsAttendance = baseUrl + "class_attendence_by_classId_for_android/";
    public static String allemployeeAttendanceGraph=baseUrl + "employee_monthly_attendence/";
    public static String lessionTrackerGraph=baseUrl + "all_subjects_of_chapters_completed_topics/";
   public static String addChaptersBulk=baseUrl + "chaptersbulk_completed_topics/";
   public static String schoolId1="schoolIdPref";
   public static String classId="classIdPref";
   public static String sectionId="sectionIdPref";
    public static String studentPresentDayUrl=baseUrl + "presentDay_student_attendence/";//day//student_id

    public static String timeTablePresentDayUrl=baseUrl + "class_timetable_by_day/";//day//sectionId
}
//http://localhost:4005/api/day_timetable_by_classId/:day/:classId