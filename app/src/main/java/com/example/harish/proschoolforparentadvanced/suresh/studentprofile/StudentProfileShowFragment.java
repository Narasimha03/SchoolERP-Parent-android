package com.example.harish.proschoolforparentadvanced.suresh.studentprofile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.harish.proschoolforparentadvanced.Constants;
import com.example.harish.proschoolforparentadvanced.R;
import com.example.harish.proschoolforparentadvanced.StudentProfileBackTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by harish on 12/27/2017.
 */

public class StudentProfileShowFragment extends Fragment implements StudentProfileBackTask.StudentProfile{

    View studentProfileView;
    Context mContext;
    String studentId;
    ImageView studentImage;
    TextView studentName, pAdmNo,pClass, currentCity, currentState, currentPin, permAddress, permCity, permState, permPin,pRollNo, pGender, pCategory, pContact, pSection, studentFatherName, pEmailId, studentNameCard, surname, doa, dob, aadharNumber, bloodGroup, busRoute, studentFatherOccupation, studentFatherContact, studentMotherContact, studentMotherOccupation, studentMotherName, studentGuardianName, studentGuardianOccupation, studentGuardianRelation, studentGuardianContact, currentAddress, permanentAddress;
    ProgressBar imagePrograss;
    SharedPreferences sharedPreferences2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        studentProfileView = inflater.inflate(R.layout.student_profile_show_layout_three, container, false);
        mContext = getActivity();


        studentImage = (ImageView) studentProfileView.findViewById(R.id.imgShow);
        studentName = (TextView) studentProfileView.findViewById(R.id.studentName);
        studentNameCard = (TextView) studentProfileView.findViewById(R.id.studentNameCard);
        pAdmNo = (TextView) studentProfileView.findViewById(R.id.pAdmNo);
        surname = (TextView) studentProfileView.findViewById(R.id.surname);
        doa = (TextView) studentProfileView.findViewById(R.id.doa);
        pRollNo = (TextView) studentProfileView.findViewById(R.id.pRollNo);
        dob = (TextView) studentProfileView.findViewById(R.id.dob);
        pGender = (TextView) studentProfileView.findViewById(R.id.pGender);
        bloodGroup = (TextView) studentProfileView.findViewById(R.id.bloodGroup);
        pCategory = (TextView) studentProfileView.findViewById(R.id.pCategory);
        aadharNumber = (TextView) studentProfileView.findViewById(R.id.aadharNumber);
        busRoute = (TextView) studentProfileView.findViewById(R.id.busRoute);
        pContact = (TextView) studentProfileView.findViewById(R.id.pContact);
        pSection = (TextView) studentProfileView.findViewById(R.id.pSection);
        pClass = (TextView) studentProfileView.findViewById(R.id.pClass);
        pEmailId = (TextView) studentProfileView.findViewById(R.id.pEmail);
        imagePrograss = (ProgressBar) studentProfileView.findViewById(R.id.progress);
        //religion = (TextView) studentProfileView.findViewById(R.id.religion);

        studentFatherName = (TextView) studentProfileView.findViewById(R.id.fatherName);
        studentFatherOccupation = (TextView) studentProfileView.findViewById(R.id.fatherOccupation);
        studentFatherContact = (TextView) studentProfileView.findViewById(R.id.fatherContact);

        studentMotherName = (TextView) studentProfileView.findViewById(R.id.motherName);
        studentMotherOccupation = (TextView) studentProfileView.findViewById(R.id.motherOccupation);
        studentMotherContact = (TextView) studentProfileView.findViewById(R.id.motherContact);

        studentGuardianName = (TextView) studentProfileView.findViewById(R.id.guardianName);
        studentGuardianOccupation = (TextView) studentProfileView.findViewById(R.id.guardianOccupation);
        studentGuardianContact = (TextView) studentProfileView.findViewById(R.id.guardianContact);
        studentGuardianRelation = (TextView) studentProfileView.findViewById(R.id.guardianRelation);

        currentAddress = (TextView) studentProfileView.findViewById(R.id.currentAddress);
        currentCity = (TextView) studentProfileView.findViewById(R.id.currentCity);
        currentState = (TextView) studentProfileView.findViewById(R.id.currentState);
        currentPin = (TextView) studentProfileView.findViewById(R.id.currentPin);

        permAddress = (TextView) studentProfileView.findViewById(R.id.permAddress);
        permCity = (TextView) studentProfileView.findViewById(R.id.permCity);
        permState = (TextView) studentProfileView.findViewById(R.id.permState);
        permPin = (TextView) studentProfileView.findViewById(R.id.permPin);

        SharedPreferences sharedPreferences1 = mContext.getSharedPreferences("studentDetails", MODE_PRIVATE);
        studentId = sharedPreferences1.getString(Constants.studentId, "");

        Log.e("student id for Profile", studentId);

        new StudentProfileBackTask(getActivity(), StudentProfileShowFragment.this).execute(studentId);


        return studentProfileView;
    }

    @Override
    public void studentProfile(String result) throws JSONException {

        System.out.println("hello profile");

        if (result != null && !result.isEmpty()) {
            //  studentsDetailsArrayList.clear();
            String stdName;
            String imageName = "";

            String currAddress = "";
            String currCity = "";
            String currState = "";
            String currPin = "";

            String permaAddress = "";
            String permaCity = "";
            String permaState = "";
            String permaPin = "";

            String className = "";
            String sectionName = "";
            String fatherName = "";
            String fatherOccupation = "";
            String fatherContact = "";
            String motherName = "";
            String motherOccupation = "";
            String motherContact = "";
            String guardianName = "";
            String guardianOccupation = "";
            String guardianContact = "";
            String guardianRelation = "";

            String imageurl = null;


            JSONObject student = new JSONObject(result);
            JSONArray jsonArray = student.getJSONArray("students");
            System.out.println("Data" + jsonArray);


            if (jsonArray.length() > 0) {
                int count = 0;
                while (count < jsonArray.length()) {
                    JSONObject jsonObject = jsonArray.getJSONObject(count);
                    stdName = jsonObject.getString("first_name") + jsonObject.getString("last_name");
                    // Parent Array....


                    System.out.println("student Profile Json Data" + jsonArray);
                    studentNameCard.setText(stdName);
                    studentName.setText(stdName);
                    aadharNumber.setText(jsonObject.getString("aadhar_no"));
                    surname.setText(jsonObject.getString("surname"));
                    pAdmNo.setText(jsonObject.getString("admission_no"));
                    doa.setText(jsonObject.getString("admission_date"));
                    dob.setText(jsonObject.getString("dob"));
                    busRoute.setText(jsonObject.getString("bus_route_id"));
                    pRollNo.setText(jsonObject.getString("roll_no"));
                    bloodGroup.setText(jsonObject.getString("blood_group"));
                    pGender.setText(jsonObject.getString("gender"));
                    pCategory.setText(jsonObject.getString("category"));
                    pContact.setText(jsonObject.getString("phone"));
                    //pSection.setText(jsonObject.getString("section_id"));
                    pEmailId.setText(jsonObject.getString("email"));
                    //religion.setText(jsonObject.getString("religion"));

//                    currentAddress.setText(jsonObject.getString("cur_address"));
 //                   currentCity.setText(jsonObject.getString("cur_city"));
 //                   currentState.setText(jsonObject.getString("cur_state"));
  //                  currentPin.setText(jsonObject.getString("cur_pincode"));

                  //  permAddress.setText(jsonObject.getString("perm_address"));
                  //  permanentAddress.setText(jsonObject.getString("perm_city"));
                  //  currentAddress.setText(jsonObject.getString("perm_state"));
                   // permanentAddress.setText(jsonObject.getString("perm_pincode"));


                    //studentImage
                    if (!jsonObject.isNull("parents")) {
                        JSONArray imageArray = jsonObject.getJSONArray("parents");
                        int p = 0;
                        while (p < imageArray.length()) {
                            JSONObject parentObject = imageArray.getJSONObject(0);
                            JSONObject parentObject1 = imageArray.getJSONObject(1);
                            JSONObject parentObject2 = imageArray.getJSONObject(2);


                            fatherName = parentObject.getString("parent_name");
                            fatherOccupation = parentObject.getString("occupation");
                            fatherContact = parentObject.getString("parent_contact");
                            motherName = parentObject1.getString("parent_name");
                            motherOccupation = parentObject1.getString("occupation");
                            motherContact = parentObject1.getString("parent_contact");
                            guardianName = parentObject2.getString("parent_name");
                            guardianOccupation = parentObject2.getString("occupation");
                            guardianContact = parentObject2.getString("parent_contact");
                            guardianRelation = parentObject2.getString("parent_relation");

                            p++;
                        }

                    }

                    studentFatherName.setText(fatherName);
                    studentFatherOccupation.setText(fatherOccupation);
                    studentFatherContact.setText(fatherContact);

                    studentMotherName.setText(motherName);
                    studentMotherOccupation.setText(motherOccupation);
                    studentMotherContact.setText(motherContact);

                    studentGuardianName.setText(guardianName);
                    studentGuardianOccupation.setText(guardianOccupation);
                    studentGuardianContact.setText(guardianContact);
                    studentGuardianRelation.setText(guardianRelation);


                    if (!jsonObject.isNull("school_classes")) {
                        JSONArray imageArray = jsonObject.getJSONArray("school_classes");
                        int p = 0;
                        while (p < imageArray.length()) {
                            JSONObject parentObject = imageArray.getJSONObject(p);
                            className = parentObject.getString("name");

                            p++;
                        }


                    }
                    pClass.setText(className);

                    if (!jsonObject.isNull("sections")) {
                        JSONArray imageArray = jsonObject.getJSONArray("sections");
                        int p = 0;
                        while (p < imageArray.length()) {
                            JSONObject parentObject = imageArray.getJSONObject(p);
                            sectionName = parentObject.getString("name");

                            p++;
                        }


                    }

                    pSection.setText(sectionName);

                    if (!jsonObject.isNull("current_address")) {
                        JSONArray imageArray = jsonObject.getJSONArray("current_address");
                        int p = 0;
                        while (p < imageArray.length()) {
                            JSONObject parentObject1 = imageArray.getJSONObject(0);

                            currAddress = parentObject1.getString("cur_address");
                            currCity = parentObject1.getString("cur_city");
                            currState = parentObject1.getString("cur_state");
                            currPin = parentObject1.getString("cur_pincode");

                            p++;
                        }


                    }

                    currentAddress.setText(currAddress);
                    currentCity.setText(currCity);
                    currentState.setText(currState);
                    currentPin.setText(currPin);


                    if (!jsonObject.isNull("permanent_address")) {
                        JSONArray imageArray = jsonObject.getJSONArray("permanent_address");
                        int p = 0;
                        while (p < imageArray.length()) {
                            JSONObject parentObject1 = imageArray.getJSONObject(0);

                            permaAddress = parentObject1.getString("perm_address");
                            permaCity = parentObject1.getString("perm_city");
                            permaState = parentObject1.getString("perm_state");
                            permaPin = parentObject1.getString("perm_pincode");

                            p++;
                        }


                    }

                    permAddress.setText(permaAddress);
                    permCity.setText(permaCity);
                    permState.setText(permaState);
                    permPin.setText(permaPin);


                    if (!jsonObject.isNull("studentImage")) {
                        JSONArray imageArray = jsonObject.getJSONArray("studentImage");
                        int p = 0;
                        while (p < imageArray.length()) {
                            JSONObject parentObject = imageArray.getJSONObject(p);
                            imageName = parentObject.getString("filename");

                            p++;
                        }


                    }

                    imageurl = Constants.singleImage + imageName;
                    count++;
                }


                Glide.with(mContext)
                        .load(imageurl).listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        imagePrograss.setVisibility(View.GONE);
                        return false;

                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        imagePrograss.setVisibility(View.GONE);

                        return false;
                    }
                })
                        .into(studentImage);


            } else {

                Toast.makeText(getActivity(), "No Records Found....!", Toast.LENGTH_LONG).show();
            }

        }


    }
}
