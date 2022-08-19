<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;
use App\Models\Income;
use App\Models\Intype;
use App\Models\Outcome;
use App\Models\Outtype;

use DB;

class ThongkeController extends Controller
{
    //
    public function month(Request $request)
    {
        $user_id = $request->user_id;
        $month = $request->month;
        $year = $request->year;

        $incomereturn = null;
        $outcomereturn = null;
        $success = false;

        $income = DB::table('sumincome')
            ->where('user_id', $user_id)
            ->where('si_month', $month)
            ->where('si_year', $year)
            ->first();

        $outcome = DB::table('sumoutcome')
            ->where('user_id', $user_id)
            ->where('so_month', $month)
            ->where('so_year', $year)
            ->first();
        
        if($income) 
        {
            $incomereturn = $income;
            $success = true;
        }

        if($outcome)
        {
            $outcomereturn = $outcome;
            $success = true;
        }

        return response()->json([
            'success'=>$success,
            'sumincome'=> $incomereturn,
            'sumoutcome'=> $outcomereturn,
        ]);
    }

    public function year(Request $request)
    {
        $user_id = $request->user_id;
        $year = $request->year;

        $income1 = null; $outcome1 = null;
        $income2 = null; $outcome2 = null;
        $income3 = null; $outcome3 = null;
        $income4 = null; $outcome4 = null;
        $income5 = null; $outcome5 = null;
        $income6 = null; $outcome6 = null;
        $income7 = null; $outcome7 = null;
        $income8 = null; $outcome8 = null;
        $income9 = null; $outcome9 = null;
        $income10 = null; $outcome10 = null;
        $income11 = null; $outcome11 = null;
        $income12 = null; $outcome12 = null;
// 1
        $income1db = DB::table('sumincome')
        ->where('user_id', $user_id)
        ->where('si_month', 1)
        ->where('si_year', $year)
        ->first();

        $outcome1db = DB::table('sumoutcome')
        ->where('user_id', $user_id)
        ->where('so_month', 1)
        ->where('so_year', $year)
        ->first();
// 2
        $income2db = DB::table('sumincome')
        ->where('user_id', $user_id)
        ->where('si_month', 2)
        ->where('si_year', $year)
        ->first();

        $outcome2db = DB::table('sumoutcome')
        ->where('user_id', $user_id)
        ->where('so_month', 2)
        ->where('so_year', $year)
        ->first();
// 3
        $income3db = DB::table('sumincome')
        ->where('user_id', $user_id)
        ->where('si_month', 3)
        ->where('si_year', $year)
        ->first();

        $outcome3db = DB::table('sumoutcome')
        ->where('user_id', $user_id)
        ->where('so_month', 3)
        ->where('so_year', $year)
        ->first();
// 4
        $income4db = DB::table('sumincome')
        ->where('user_id', $user_id)
        ->where('si_month', 4)
        ->where('si_year', $year)
        ->first();

        $outcome4db = DB::table('sumoutcome')
        ->where('user_id', $user_id)
        ->where('so_month', 4)
        ->where('so_year', $year)
        ->first();
// 5
        $income5db = DB::table('sumincome')
        ->where('user_id', $user_id)
        ->where('si_month', 5)
        ->where('si_year', $year)
        ->first();

        $outcome5db = DB::table('sumoutcome')
        ->where('user_id', $user_id)
        ->where('so_month', 5)
        ->where('so_year', $year)
        ->first();
// 6
        $income6db = DB::table('sumincome')
        ->where('user_id', $user_id)
        ->where('si_month', 6)
        ->where('si_year', $year)
        ->first();

        $outcome6db = DB::table('sumoutcome')
        ->where('user_id', $user_id)
        ->where('so_month', 6)
        ->where('so_year', $year)
        ->first();

// 7
        $income7db = DB::table('sumincome')
        ->where('user_id', $user_id)
        ->where('si_month', 7)
        ->where('si_year', $year)
        ->first();

        $outcome7db = DB::table('sumoutcome')
        ->where('user_id', $user_id)
        ->where('so_month', 7)
        ->where('so_year', $year)
        ->first();

// 8
        $income8db = DB::table('sumincome')
        ->where('user_id', $user_id)
        ->where('si_month', 8)
        ->where('si_year', $year)
        ->first();

        $outcome8db = DB::table('sumoutcome')
        ->where('user_id', $user_id)
        ->where('so_month', 8)
        ->where('so_year', $year)
        ->first();

// 9
        $income9db = DB::table('sumincome')
        ->where('user_id', $user_id)
        ->where('si_month', 9)
        ->where('si_year', $year)
        ->first();

        $outcome9db = DB::table('sumoutcome')
        ->where('user_id', $user_id)
        ->where('so_month', 9)
        ->where('so_year', $year)
        ->first();

// 10
        $income10db = DB::table('sumincome')
        ->where('user_id', $user_id)
        ->where('si_month', 10)
        ->where('si_year', $year)
        ->first();

        $outcome10db = DB::table('sumoutcome')
        ->where('user_id', $user_id)
        ->where('so_month', 10)
        ->where('so_year', $year)
        ->first();

// 11
        $income11db = DB::table('sumincome')
        ->where('user_id', $user_id)
        ->where('si_month', 11)
        ->where('si_year', $year)
        ->first();

        $outcome11db = DB::table('sumoutcome')
        ->where('user_id', $user_id)
        ->where('so_month', 11)
        ->where('so_year', $year)
        ->first();

// 12
        $income12db = DB::table('sumincome')
        ->where('user_id', $user_id)
        ->where('si_month', 12)
        ->where('si_year', $year)
        ->first();

        $outcome12db = DB::table('sumoutcome')
        ->where('user_id', $user_id)
        ->where('so_month', 12)
        ->where('so_year', $year)
        ->first();

// 
        if($income1db) 
        {
            $income1 = $income1db;
        }

        if($outcome1db)
        {
            $outcome1 = $outcome1db;
        }

        if($income2db) 
        {
            $income2 = $income2db;
        }

        if($outcome2db)
        {
            $outcome2 = $outcome2db;
        }

        if($income3db) 
        {
            $income3 = $income3db;
        }

        if($outcome3db)
        {
            $outcome3 = $outcome3db;
        }

        if($income4db) 
        {
            $income4 = $income4db;
        }

        if($outcome4db)
        {
            $outcome4 = $outcome4db;
        }

        if($income5db) 
        {
            $income5 = $income5db;
        }

        if($outcome5db)
        {
            $outcome5 = $outcome5db;
        }

        if($income6db) 
        {
            $income6 = $income6db;
        }

        if($outcome6db)
        {
            $outcome6 = $outcome6db;
        }

        if($income7db) 
        {
            $income7 = $income7db;
        }

        if($outcome7db)
        {
            $outcome7 = $outcome7db;
        }

        if($income8db) 
        {
            $income8 = $income8db;
        }

        if($outcome8db)
        {
            $outcome8 = $outcome8db;
        }

        if($income9db) 
        {
            $income9 = $income9db;
        }

        if($outcome9db)
        {
            $outcome9 = $outcome9db;
        }

        if($income10db) 
        {
            $income10 = $income10db;
        }

        if($outcome10db)
        {
            $outcome10 = $outcome10db;
        }

        if($income11db) 
        {
            $income11 = $income11db;
        }

        if($outcome11db)
        {
            $outcome11 = $outcome11db;
        }

        if($income12db) 
        {
            $income12 = $income12db;
        }

        if($outcome12db)
        {
            $outcome12 = $outcome12db;
        }


        return response()->json([
            'success'=>true,
            'income1'=> $income1,
            'outcome1'=> $outcome1,
            'income2'=> $income2,
            'outcome2'=> $outcome2,
            'income3'=> $income3,
            'outcome3'=> $outcome3,
            'income4'=> $income4,
            'outcome4'=> $outcome4,
            'income5'=> $income5,
            'outcome5'=> $outcome5,
            'income6'=> $income6,
            'outcome6'=> $outcome6,
            'income7'=> $income7,
            'outcome7'=> $outcome7,
            'income8'=> $income8,
            'outcome8'=> $outcome8,
            'income9'=> $income9,
            'outcome9'=> $outcome9,
            'income10'=> $income10,
            'outcome10'=> $outcome10,
            'income11'=> $income11,
            'outcome11'=> $outcome11,
            'income12'=> $income12,
            'outcome12'=> $outcome12,
        ]);
    }
}
