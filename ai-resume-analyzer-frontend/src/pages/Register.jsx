function Register() {
  return (
    <div className="container mt-5">
      <div className="row justify-content-center">

        <div className="col-md-5">

          <div className="card shadow p-4">

            <h2 className="text-center mb-4">
              Register
            </h2>

            <input
              type="text"
              placeholder="Enter Name"
              className="form-control mb-3"
            />

            <input
              type="email"
              placeholder="Enter Email"
              className="form-control mb-3"
            />

            <input
              type="password"
              placeholder="Enter Password"
              className="form-control mb-3"
            />

            <button className="btn btn-success w-100">
              Register
            </button>

          </div>

        </div>

      </div>
    </div>
  );
}

export default Register;