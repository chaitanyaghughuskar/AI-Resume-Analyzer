import axios from "axios";

const API_URL = "http://localhost:8080/api/resume";

export const analyzeResume = async (file) => {

    const formData = new FormData();

    formData.append("file", file);

    const response = await axios.post(
        `${API_URL}/analyze`,
        formData
    );

    return response.data;
};

export const matchJD = async (resumeText, jobDescription) => {

    const response = await axios.post(
        `${API_URL}/match-jd`,
        {
            resumeText,
            jobDescription
        }
    );

    return response.data;
};