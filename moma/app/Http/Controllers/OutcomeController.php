<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;
use App\Models\Income;
use App\Models\Intype;
use App\Models\Outcome;
use App\Models\Outtype;
use DB;

class OutcomeController extends Controller
{
    //
    public function getOut(Request $request) {
        $userid = $request->user_id;
        $outcome = Outcome::where(["user_id" => $userid])->get();
        if(isset($outcome))
        {
            return response()->json([
                'success'=>true,
                'outcome'=> $outcome,
            ]);
        }
        else
        {
            return response()->json([
                'success' => false,
                'outcome'=> null,
            ]);
        }
    }

    public function getOutDetail(Request $request) {
        $outcomeid = $request->outcome_id;
        $outcome = Income::where(["outcome_id" => $outcomeid])->first();
        $outtype = Intype::where(["outtype_id" => $outcome->outtype_id])->first();
        if(isset($outcome))
        {
            return response()->json([
                'success'=>true,
                'outcome'=> $outcome,
                'outtype'=> $outtype,
            ]);
        }
        else
        {
            return response()->json([
                'success' => false,
                'outcome'=> null,
                'outtype'=> null,
            ]);
        }
    }

    public function addOut(Request $request) {
        $outcome = new Outcome();

        $outcome->outcome_name = $request->outcome_name;
        $outcome->outcome_val = $request->outcome_val;
        $outcome->outcome_val_2 = $request->outcome_val_2;
        $outcome->outcome_valstring = $request->outcome_valstring;
        $outcome->outtype_id = $request->outtype_id;
        $outcome->outcome_day = $request->outcome_day;
        $outcome->outcome_month = $request->outcome_month;
        $outcome->outcome_year = $request->outcome_year;
        $outcome->user_id = $request->user_id;
        
        $outcome->save();

        $sum = DB::table('sumoutcome')
            ->where('user_id', $request->user_id)
            ->where('so_month', $request->outcome_month)
            ->where('so_year', $request->outcome_year)
            ->first();

        if ($sum)
        {
            $data = array();
            $data['so_val'] = $sum->so_val + $request->outcome_val;
            $data['so_val_2'] = $sum->so_val_2 + $request->outcome_val_2;
            DB::table('sumoutcome')
            ->where('user_id',$request->user_id)
            ->where('so_month',$request->outcome_month)
            ->where('so_year',$request->outcome_year)
            ->update($data);
        }
        else 
        {
            $data = array();
            $data['user_id'] = $request->user_id;
            $data['so_val'] = $request->outcome_val;
            $data['so_val_2'] = $request->outcome_val_2;
            $data['so_month'] = $request->outcome_month;
            $data['so_year'] = $request->outcome_year;
            DB::table('sumoutcome')->insert($data);
        }
        
        $list = Outcome::where(["user_id" => $request->user_id])->get();

        return response()->json([
            'success'=>true,
            'outcome'=> $list,
        ]);
    }

    public function delOut(Request $request) {
        $income_id = $request->outcome_id;

        $in = DB::table('outcomes')->where('outcome_id',$income_id)->first();

        $sum = DB::table('sumoutcome')
            ->where('user_id', $in->user_id)
            ->where('so_month', $in->outcome_month)
            ->where('so_year', $in->outcome_year)
            ->first();

        $data = array();
        $data['so_val'] = $sum->so_val - $in->outcome_val;
        $data['so_val_2'] = $sum->so_val_2 - $in->outcome_val_2;

        DB::table('sumoutcome')
            ->where('user_id',$in->user_id)
            ->where('so_month',$in->outcome_month)
            ->where('so_year',$in->outcome_year)
            ->update($data);

        DB::table('outcomes')->where('outcome_id',$income_id)->delete();

        $list = Outcome::where(["user_id" => $request->user_id])->get();

        return response()->json([
            'success'=>true,
            'outcome'=> $list,
        ]);
    }
}
