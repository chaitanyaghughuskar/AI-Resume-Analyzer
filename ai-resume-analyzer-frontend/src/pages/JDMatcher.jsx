import { useState } from "react";
import { matchJD } from "../services/ResumeService";

function JDMatcher() {

    const [resumeText, setResumeText] = useState("");
    const [jobDescription, setJobDescription] = useState("");
    const [result, setResult] = useState("");

    const handleMatch = async () => {

        try {

            const response = await matchJD(
                resumeText,
                jobDescription
            );

            setResult(response.result);

        } catch (error) {

            console.error(error);

            alert("Error matching JD");
        }
    };

    return (
        <div className="container mt-5">

            <div className="card p-4 shadow">

                <h2 className="mb-4">
                    JD Matching
                </h2>

                <textarea
                    className="form-control mb-3"
                    rows="6"
                    placeholder="Paste Resume Text"
                    value={resumeText}
                    onChange={(e) =>
                        setResumeText(e.target.value)
                    }
                />

                <textarea
                    className="form-control mb-3"
                    rows="6"
                    placeholder="Paste Job Description"
                    value={jobDescription}
                    onChange={(e) =>
                        setJobDescription(e.target.value)
                    }
                />

                <button
                    className="btn btn-success"
                    onClick={handleMatch}
                >
                    Match JD
                </button>

            </div>

            {result && (

                <div className="card p-4 shadow mt-4">

                    <h3>Match Result</h3>

                    <pre>{result}</pre>

                </div>

            )}

        </div>
    );
}

export default JDMatcher;