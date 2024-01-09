package com.es.questions.controllers;

import com.es.questions.model.QuestionCMSComponentModel;

public interface QuestionsControllerConstants {
    interface Actions {
        interface Cms{
            String _Prefix = "/view/";
            String _Suffix = "Controller";

            String QuestionCMSComponent = _Prefix + QuestionCMSComponentModel._TYPECODE + _Suffix;
        }
    }
}
