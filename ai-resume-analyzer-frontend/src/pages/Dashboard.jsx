import { useState } from "react";
import { analyzeResume, matchJD } from "../services/ResumeService";

function Dashboard() {

    const [file, setFile] = useState(null);
    const [atsResult, setAtsResult] = useState("");

    const [resumeText, setResumeText] = useState("");
    const [jobDescription, setJobDescription] = useState("");
    const [jdResult, setJdResult] = useState("");

    const handleAnalyze = async () => {

        if (!file) {
            alert("Please select a resume");
            return;
        }

        try {

            const response = await analyzeResume(file);

            setAtsResult(response.analysis);

        } catch (error) {

            console.error(error);

            alert("Error analyzing resume");
        }
    };

    const handleMatch = async () => {

        try {

            const response = await matchJD(
                resumeText,
                jobDescription
            );

            setJdResult(response.result);

        } catch (error) {

            console.error(error);

            alert("Error matching JD");
        }
    };

    return (
        <div className="container mt-5 mb-5">

            <h1 className="text-center text-primary fw-bold">
                AI Resume Analyzer
            </h1>
            <p className="text-center text-secondary mb-4">
    Upload your resume and get AI-powered ATS analysis
    and Job Description matching instantly.
            </p>

            {/* ATS Analysis */}

            <div className="card shadow p-4 mt-4">

                <h3 className="text-center">
                    Resume ATS Analysis
                </h3>

                <input
                    type="file"
                    className="form-control mt-3"
                    onChange={(e) => setFile(e.target.files[0])}
                />

                <button
                    className="btn btn-primary mt-3"
                    onClick={handleAnalyze}
                >
                    Analyze Resume
                </button>

            </div>

            {atsResult && (

                <div className="card shadow p-4 mt-4">

                    <h3 className="text-success">
                        ATS Analysis Result
                    </h3>

                    <pre style={{ whiteSpace: "pre-wrap" }}>
                        {atsResult}
                    </pre>

                </div>

            )}

            {/* JD Matching */}

            <div className="card shadow p-4 mt-4">

                <h3 className="text-center">
                    Job Description Matching
                </h3>

                <textarea
                    className="form-control mt-3"
                    rows="6"
                    placeholder="Paste Resume Text"
                    value={resumeText}
                    onChange={(e) =>
                        setResumeText(e.target.value)
                    }
                />

                <textarea
                    className="form-control mt-3"
                    rows="6"
                    placeholder="Paste Job Description"
                    value={jobDescription}
                    onChange={(e) =>
                        setJobDescription(e.target.value)
                    }
                />

                <button
                    className="btn btn-success mt-3"
                    onClick={handleMatch}
                >
                    Match Job Description
                </button>

            </div>

            {jdResult && (

                <div className="card shadow p-4 mt-4">

                    <h3 className="text-success">
                        JD Match Result
                    </h3>

                    <pre style={{ whiteSpace: "pre-wrap" }}>
                        {jdResult}
                    </pre>

                </div>

            )}
           <div className="text-center mt-5 mb-3 text-secondary">
    <small>
        Built with React, Spring Boot & Groq AI
    </small>

    <br />
    <br />

    <small>
        Developed by Chaitanya Ghughuskar
    </small>

    <br />

    <small>
    📧
    <a
        href="mailto:chaitanyaghughuskar@gmail.com"
        className="ms-1"
    >
        chaitanyaghughuskar@gmail.com
    </a>
</small>

    <br />

    <small>
        🔗 LinkedIn:
        <a
            href="https://www.linkedin.com/in/chaitanya-ghughuskar/"
            target="_blank"
            rel="noreferrer"
            className="ms-1"
        >
            Chaitanya Ghughuskar
        </a>
    </small>
</div>

        </div>
    );
}

export default Dashboard;