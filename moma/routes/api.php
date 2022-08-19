<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

use App\Http\Controllers\AccountController;
use App\Http\Controllers\IncomeController;
use App\Http\Controllers\IntypeController;
use App\Http\Controllers\OutcomeController;
use App\Http\Controllers\OuttypeController;
use App\Http\Controllers\ThongkeController;

use App\Models\User;
use App\Models\Income;
use App\Models\Intype;
use App\Models\Outcome;
use App\Models\Outtype;


/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

// user
Route::post("getUser", [AccountController::class, 'login']);
Route::post("register", [AccountController::class, 'register']);
Route::post("getUserDetail", [AccountController::class, 'infor']);

// income
Route::post("getIncome", [IncomeController::class, 'getIn']);
Route::post("getIncomeByType", [IncomeController::class, 'getInByType']);
Route::post("getIncomeDetail", [IncomeController::class, 'getInDetail']);
Route::post("getIncomeDetailEdit", [IncomeController::class, 'getInDetailEdit']);
Route::post("addIncome", [IncomeController::class, 'addIn']);
Route::post("delIncome", [IncomeController::class, 'delIn']);
Route::post("editIncome", [IncomeController::class, 'editIn']);

// intype
Route::post("getIntype", [IntypeController::class, 'getType']);
Route::post("addIntype", [IntypeController::class, 'addType']);

// outcome
Route::post("getOutcome", [OutcomeController::class, 'getOut']);
Route::post("getOutcomeDetail", [OutcomeController::class, 'getOutDetail']);
Route::post("addOutcome", [OutcomeController::class, 'addOut']);
Route::post("delOutcome", [OutcomeController::class, 'delOut']);

// outtype
Route::post("getOuttype", [OuttypeController::class, 'getType']);
Route::post("addOuttype", [OuttypeController::class, 'addType']);

// thongke
Route::post("inoutPerMonth", [ThongkeController::class, 'month']);
Route::post("inoutPerYear", [ThongkeController::class, 'year']);